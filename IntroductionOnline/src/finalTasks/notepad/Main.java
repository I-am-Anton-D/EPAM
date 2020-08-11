package finalTasks.notepad;

import finalTasks.books.book.Book;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Predicate;
import javax.sound.midi.Soundbank;

public class Main {


    private static final NoteRepository noteRepository = new NoteRepository();
    private static final int LIMIT = 5;
    private static int offset = 0;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to notepad");
        while (true) {
            greeting();
        }
    }

    private static void greeting() {
        System.out.println("[1] List notes");
        System.out.println("[2] Add note");
        System.out.println("[3] Search notes");
        System.out.println("[4] Exit");
        String input = scanner.nextLine();
        int i = -1;
        try {
            i = Integer.parseInt(input);
            if (i < 0 || i > 4) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        }

        if (i == 1) {
            listNotes();
        }
        if (i == 2) {
            addNote();
        }
        if (i == 3) {
            searchNote();
        }
        if (i == 4) {
            noteRepository.saveToFile();
            System.exit(0);
        }
    }

    private static void searchNote() {
        ArrayList<Predicate<Note>> predicates = new ArrayList<>();

        System.out.println("Search by subject? 1 - YES, 0 - NO");
        String input = scanner.nextLine();
        int i = -1;
        try {
            i = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        }
        if (i==1) {
            System.out.println("Enter subject: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                predicates.add(noteRepository.getPredicateBySubject(input));
            } else {
                System.out.println("Empty query. Ignore.");
            }
        }

        System.out.println("Search by email? 1 - YES, 0 - NO");
        input = scanner.nextLine();
        i = -1;
        try {
            i = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        }
        if (i==1) {
            System.out.println("Enter email: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                predicates.add(noteRepository.getPredicateByMail(input));
            } else {
                System.out.println("Empty query. Ignore.");
            }
        }

        System.out.println("Search by date? 1 - YES, 0 - NO");
        input = scanner.nextLine();
        i = -1;
        try {
            i = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        }
        if (i==1) {
            System.out.println("Enter date (yyyy-MM-dd) ");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(input);
                } catch (ParseException e) {
                    System.out.println("Wrong date format");
                }
                predicates.add(noteRepository.getPredicateByDate(input));
            } else {
                System.out.println("Empty query. Ignore.");
            }
        }

        System.out.println("Search by msg? 1 - YES, 0 - NO");
        input = scanner.nextLine();
        i = -1;
        try {
            i = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        }
        if (i==1) {
            System.out.println("Enter query of msg: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                predicates.add(noteRepository.getPredicateByMsg(input));
            } else {
                System.out.println("Empty query. Ignore.");
            }
        }

        if (predicates.size()==0) {
            System.out.println("No parameters to search");
            return;
        }
        ArrayList<Note> result = noteRepository.search(predicates.stream().reduce(x->true, Predicate::and));
        if (result==null || result.size() == 0) {
            System.out.println("Notes not found");
        } else {
            System.out.println("Searched notes: ");
            result.forEach(System.out::println);
        }
        System.out.println("Press Enter to continue");
        scanner.nextLine();
    }

    private static void addNote() {

        Note note = new Note();

        System.out.println("Enter subject");
        String input = scanner.nextLine();
        if (!input.isEmpty()) {
            note.setSubject(input);
        }
        System.out.println("Enter new message");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            note.setMsg(input);
        }
        System.out.println("Enter new email");
        input = scanner.nextLine();

        while (!input.matches("\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*\\.\\w{2,4}")) {
            System.out.println("Wrong email. Repeat: ");
            input = scanner.nextLine();
        }

        if (!input.isEmpty()) {
            note.setEmail(input);
        }
        note.setDate(new Date());
        noteRepository.create(note);
        System.out.println("Note added");
        System.out.println("Press Enter to continue");
        scanner.nextLine();
}

    private static void listNotes() {
        while (true) {
            System.out.println("Offset = " + offset + ", Limit = " + LIMIT);
            noteRepository.getNotes(offset, LIMIT).forEach(System.out::println);
            System.out
                .println(
                    "[1] Next [2] Prev [3] Update [4] Delete [5] Sort by subject [6] Sort by email [7] Sort by date (default) [8] Back to menu");
            String input = scanner.nextLine();
            int i = 0;
            try {
                i = Integer.parseInt(input);
                if (i <= 0 || i > 8) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input");
            }

            if (i == 1) {
                if (offset + LIMIT < noteRepository.getSize() - 1) {
                    offset += LIMIT;
                }
            }

            if (i == 2) {
                if (offset != 0) {
                    offset -= LIMIT;
                }
            }

            if (i == 3) {
                updateNote();
            }

            if (i == 4) {
                deleteNote();
            }

            if (i == 5) {
                sort(Comparator.comparing(Note::getSubject));
            }
            if (i == 6) {
                sort(Comparator.comparing(Note::getEmail));
            }
            if (i == 7) {
                sort(Comparator.comparing(Note::getDate).reversed());
            }

            if (i == 8) {
                offset = 0;
                break;
            }
        }
    }

    private static void sort(Comparator<Note> comparator) {
        noteRepository.sort(comparator);
        offset = 0;
    }

    private static void deleteNote() {
        System.out.println("Enter id of note: ");
        String input = scanner.nextLine();
        int i = 0;
        try {
            i = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        }
        Note note = noteRepository.getNoteById(i);
        if (note == null) {
            System.out.println("Note with specific id do not exist");
        } else {
            noteRepository.delete(note);
            System.out.println("Note deleted");
        }
        System.out.println("Press Enter to continue");
        scanner.nextLine();
    }

    private static void updateNote() {
        System.out.println("Enter id of note: ");
        String input = scanner.nextLine();
        int i = 0;
        try {
            i = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        }
        Note note = noteRepository.getNoteById(i);
        if (note == null) {
            System.out.println("Book with specific id do not exist");
        } else {
            System.out.println("Enter new subject (old subject = '" + note.getSubject() + "'):");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                note.setSubject(input);
            }
            System.out.println("Enter new message (old message = '" + note.getMsg() + "'):");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                note.setMsg(input);
            }

            System.out.println("Enter new email (old email = '" + note.getEmail() + "'):");
            input = scanner.nextLine();

            if (!input.matches("\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*\\.\\w{2,4}")) {
                System.out.println("Wrong email. Email do not changing");
                input = "";
            }

            if (!input.isEmpty()) {
                note.setEmail(input);
            }

            note.setDate(new Date());

            noteRepository.update(note);
            System.out.println("Note saved");
        }
        System.out.println("Press Enter to continue");
        scanner.nextLine();
    }
}
