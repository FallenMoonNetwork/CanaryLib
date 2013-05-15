package net.canarymod.tasks;

import java.util.Iterator;
import java.util.LinkedList;

import net.canarymod.Canary;
import net.canarymod.plugin.Plugin;

/**
 * Server Task Manager
 * <p>
 * The Manager for {@link ServerTask}<br>
 * If an exception occurs while running a task, it gets removed from the queue, regardless of continuous status
 *
 * @author Jason (darkdiplomat)
 */
public final class ServerTaskManager {
    private final static ServerTaskManager $;
    private final LinkedList<ServerTask> tasks;

    static {
        $ = new ServerTaskManager();
    }

    private ServerTaskManager() {
        tasks = new LinkedList<ServerTask>();
    }

    /**
     * Adds a {@link ServerTask} to the queue
     *
     * @param task
     *            the {@link ServerTask} to be added
     * @return {@code true} if successfully added; {@code false} if not
     */
    public static boolean addTask(ServerTask task) {
        synchronized ($.tasks) {
            return $.tasks.add(task);
        }
    }

    /**
     * Removes a {@link ServerTask} from the queue<br>
     * When a {@link Plugin} is disabled, it should remove it's tasks from the queue
     *
     * @param task
     *            the {@link ServerTask} to be removed
     * @return {@code true} if removed; {@code false} if not found or unable to be removed
     */
    public static boolean removeTask(ServerTask task) {
        synchronized ($.tasks) {
            return $.tasks.remove(task);
        }
    }

    /**
     * Removes all the tasks for a specified {@link Plugin}
     *
     * @param plugin
     *            the {@link Plugin} to remove tasks for
     */
    public static void removeTasksForPlugin(Plugin plugin) {
        synchronized ($.tasks) {
            Iterator<ServerTask> taskIter = $.tasks.iterator();
            while (taskIter.hasNext()) {
                ServerTask task = taskIter.next();
                if (task.getOwner().equals(plugin)) {
                    taskIter.remove();
                }
            }
        }
    }

    /**
     * Internal method called to run the tasks or decrease timers.
     */
    public static void runTasks() {
        if ($.tasks.isEmpty()) {
            // No tasks? no execution needed
            return;
        }
        synchronized ($.tasks) {
            Iterator<ServerTask> taskIter = $.tasks.iterator();
            while (taskIter.hasNext()) {
                ServerTask task = taskIter.next();
                task.decrementDelay();
                if (task.shouldExecute()) {
                    try {
                        task.run();
                    } catch (Throwable thrown) {
                        Canary.logStackTrace("An Exception occured while executing ServerTask: " + task.getClass().getSimpleName(), thrown);
                        taskIter.remove();
                        continue;
                    }
                    if (!task.isContinuous()) {
                        taskIter.remove();
                    } else {
                        task.reset();
                    }
                }
            }
        }
    }
}
