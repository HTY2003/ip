import java.util.Arrays;
import java.util.Scanner;

public class Frappe {

    public static Task[] tasks = new Task[100];
    public static int cur_idx = 0;

    public static void printUnderscoreLine() {
        System.out.println(new String(new char[50]).replace("\0", "_")
        );
    }

    public static void printTaskAdded(Task task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.getPrintOutput());
        System.out.println("Now you have " + String.valueOf(cur_idx) + (cur_idx == 1 ? " task " : " tasks ") + "in the list.");
    }

    public static void printInvalidCommand() {
        System.out.println("Invalid command format. Please try again.");
    }

    public static void updateTaskComplete(String[] words, Boolean complete) {
        if (words.length < 3) {
            if (words.length > 1) {
                if (words[1].matches("[0-9]+")) {
                    int idx = Integer.parseInt(words[1]) - 1;
                    if (idx >= 0 && idx < cur_idx) {
                        tasks[idx].setComplete(complete);
                        if (complete)
                            System.out.println("Nice! I've marked this task as done:");
                        else
                            System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(tasks[idx].getPrintOutput());
                        return;
                    }
                }
            }
        }

        printInvalidCommand();
    }

    public static void main(String[] args) {
        printUnderscoreLine();
        System.out.println("Hello! I'm Frappe.");
        System.out.println("What can I do for you?");
        printUnderscoreLine();

        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine().trim();

            printUnderscoreLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                printUnderscoreLine();
                break;
            } else {
                String[] words = input.split(" ");
                switch (input) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < cur_idx; i++)
                        System.out.println(String.format("%d. ", i + 1) + tasks[i].getPrintOutput());
                    break;
                default:
                    switch (words[0]) {
                    case "mark":
                        updateTaskComplete(words, true);
                        break;
                    case "unmark":
                        updateTaskComplete(words, false);
                        break;
                    case "todo":
                        String td = String.join(" ", Arrays.copyOfRange(words, 1, words.length)).trim();
                        if (td.length() > 0) {
                            tasks[cur_idx] = new Task(td);
                            cur_idx++;
                            printTaskAdded(tasks[cur_idx - 1]);
                        } else
                            printInvalidCommand();
                        break;
                    case "deadline":
                        String[] dl_input = String.join(" ", Arrays.copyOfRange(words, 1, words.length)).split("/by ");
                        if (dl_input.length == 2) {
                            String dl = dl_input[0].trim();
                            String by = dl_input[1].trim();

                            if (dl.length() > 0 && by.length() > 0) {
                                tasks[cur_idx] = new Deadline(dl, by);
                                cur_idx++;
                                printTaskAdded(tasks[cur_idx - 1]);
                                break;
                            }
                        }
                        printInvalidCommand();
                        break;
                    case "event":
                        String[] evnt_input = String.join(" ", Arrays.copyOfRange(words, 1, words.length)).split("/from ");
                        if (evnt_input.length == 2) {

                            String[] evnt_input2 = evnt_input[1].split("/to");

                            if (evnt_input2.length == 2) {
                                String evnt = evnt_input[0].trim();
                                String from = evnt_input2[0].trim();
                                String to = evnt_input2[1].trim();

                                if (evnt.length() > 0 && from.length() > 0 && to.length() > 0) {
                                    tasks[cur_idx] = new Event(evnt, from, to);
                                    cur_idx++;
                                    printTaskAdded(tasks[cur_idx - 1]);
                                    break;
                                }
                            }
                        }
                        printInvalidCommand();
                        break;
                    default:
                        System.out.println("What can I do for you?");
                    }
                }
            }
            printUnderscoreLine();
        }
    }
}