import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws EmptyDescriptionException, OutOfRangeException, UnknownCommandException {

        Task[] arr = new Task[100];
        int count = 0;

        System.out.println("Hello I'm Duke" + "\nWhat can I do for you?");

        //scanner
        Scanner sc = new Scanner(System.in);
        String input = "";


        while (true) {

            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("\nHere are the tasks in your list:");
                for (int i = 0; i < count; i ++) {
                    System.out.println((i + 1) + ". " + arr[i].toString());
                }
            } else if (input.contains("mark")) {
                int index = -1;
                try {
                    if (input.equals("mark")) {
                        throw new EmptyDescriptionException("Please select a task to mark");
                    } else {
                        input = input.replaceAll("mark ", "");
                        index = Integer.parseInt(input) - 1;
                    }

                    if (index > count - 1 || index < 0) {
                        throw new OutOfRangeException(index + 1);
                    } else {
                        arr[index].setDone();
                    }

                    System.out.println("Nice! I've marked this task as done:\n" +
                            arr[index].toString());
                } catch (EmptyDescriptionException | OutOfRangeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.contains("todo")) {
                try {
                    if (input.equals("todo")) {
                        throw new EmptyDescriptionException();
                    }
                    input = input.replaceAll("todo ", "");
                    arr[count] = new Todo(input);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(arr[count].toString());
                    System.out.printf( "Now you have %d tasks in the list.", count + 1);
                    count++;
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.contains("deadline")) {
                System.out.println("Got it. I've added this task:");
                input = input.replaceAll("deadline ", "");

                //string format
                String[] s_arr = input.split("/", -1); //split array
                s_arr[1] = s_arr[1].replaceAll("by ", "");
                arr[count] = new Deadline(s_arr[0], s_arr[1]);

                System.out.println(arr[count].toString());
                System.out.printf( "Now you have %d tasks in the list.", count + 1);
                count++;

            } else if (input.contains("event")) {
                System.out.println("\nGot it. I've added this task:");
                input = input.replaceAll("event ", "");

                String[] s_arr = input.split("/", -1); //split array
                s_arr[1] = s_arr[1].replaceAll("at ", "");
                arr[count] = new Event(s_arr[0], s_arr[1]);

                System.out.println(arr[count].toString());
                System.out.printf( "Now you have %d tasks in the list.", count + 1);
                count++;
            } else {
                try {
                    throw new UnknownCommandException();
                } catch (UnknownCommandException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        sc.close();


    }
}