package com.netcracker.eductr.tasks.tests;

import com.netcracker.eductr.tasks.tests.model.Task;
import com.netcracker.eductr.tasks.tests.model.Tasks;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.netcracker.eductr.tasks.tests.model.BaseCreator.isOld;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkClassExistence;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkMethodExistence;
import static com.netcracker.eductr.tasks.tests.utils.DatesTimes.*;
import static com.netcracker.eductr.tasks.tests.utils.EqualsUtil.areEqual;
import static com.netcracker.eductr.tasks.tests.utils.EqualsUtil.getMessage;
import static com.netcracker.eductr.tasks.tests.utils.TaskUtil.describeTask;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.TASKS;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.TASK_BASE;
import static com.netcracker.eductr.tasks.tests.utils.Types.methodTypes.INCOMING;
import static com.netcracker.eductr.tasks.tests.model.TaskCreator.create;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T7_TasksTest {
    @BeforeClass
    public static void init() {
        Assume.assumeTrue(checkClassExistence(TASK_BASE));
        Assume.assumeTrue(checkClassExistence(TASKS));
        Assume.assumeTrue(checkMethodExistence(INCOMING, TASKS));
        Assume.assumeFalse(isOld());
    }

    @Test
    public void part1_testIncomingInactive() {
        List<Task> input = Arrays.asList(create("A", NOW, false), create("B", FROM_NOW_1, false), create("C", FROM_NOW_3, false));
        Iterable<?> res = Tasks.incoming(input, NOW, FROM_NOW_1000);
        Assert.assertFalse("incoming(" + input + ", " + NOW + ", " + FROM_NOW_1000 + ") має бути пустим, але насправді: " + res, res.iterator().hasNext());
    }

    @Test
    public void part2_testIncoming() {
        // range: 50 60
        List<Task> ts = Arrays.asList(
                create("Simple IN", FROM_NOW_55, true),
                create("Simple OUT", FROM_NOW_10, true),
                create("Inactive OUT", NOW, FROM_NOW_1000, 1, false),
                create("Simple bound OUT", FROM_NOW_50, true),
                create("Simple bound IN", FROM_NOW_60, true),
                create("Repeat inside IN", FROM_NOW_51, FROM_NOW_58, 2, true),
                create("Repeat outside IN", NOW, FROM_NOW_100, 5, true),
                create("Repeat outside OUT", NOW, FROM_NOW_100, 22, true),
                create("Repeat left OUT", NOW, FROM_NOW_40, 1, true),
                create("Repeat right OUT", FROM_NOW_65, FROM_NOW_100, 1, true),
                create("Repeat left intersect IN 1", NOW, FROM_NOW_55, 13, true),
                create("Repeat left intersect IN 2", NOW, FROM_NOW_60, 30, true),
                create("Repeat left intersect OUT", NOW, FROM_NOW_55, 22, true),
                create("Repeat right intersect IN", FROM_NOW_55, FROM_NOW_100, 20, true)
        );
        List<String> incoming = new ArrayList<>();
        ts.stream().map(Task::getTitle).filter(t -> t.contains("IN")).forEach(incoming::add);

        Iterable<Task> res = Tasks.incoming(ts, FROM_NOW_50, FROM_NOW_60);
        String call = "incoming(" + ts.stream().map(Task::getTitle).collect(Collectors.toList()) + ", " + FROM_NOW_50 + ", " + FROM_NOW_60 + ")";

        for (Object o : res) {
            Task t = new Task(o);
            Assert.assertTrue(call + " не повинно містити " + describeTask(t), incoming.contains(t.getTitle()));
            incoming.remove(t.getTitle());
        }
        Assert.assertTrue(call + " повинно містити " + incoming, incoming.isEmpty());
    }

    @Test
    public void part3_testTimeline() {
        Task daily = create("Daily", YESTERDAY, TOMORROW, 3600*24);
        Task hourly = create("Hourly", TODAY, TOMORROW, 3600);
        Task every3h = create("Every 3 hours", TODAY_1H, TOMORROW, 3*3600);
        daily.setActive(true);
        hourly.setActive(true);
        every3h.setActive(true);

        SortedMap<LocalDateTime, Set<Task>> timeline = new TreeMap<>();
        timeline.put(TODAY, new HashSet<>(Arrays.asList(daily, hourly)));
        timeline.put(TODAY_1H, new HashSet<>(Arrays.asList(hourly, every3h)));
        timeline.put(TODAY_2H, new HashSet<>(Collections.singletonList(hourly)));
        timeline.put(TODAY_3H, new HashSet<>(Collections.singletonList(hourly)));
        timeline.put(TODAY_4H, new HashSet<>(Arrays.asList(hourly, every3h)));

        SortedMap<LocalDateTime, Set<Task>> result = Tasks.calendar(new HashSet<>(Arrays.asList(daily, hourly, every3h)), ALMOST_TODAY, TODAY_4H);
        Set<LocalDateTime> res = new HashSet<>(result.keySet());
        res.removeAll(timeline.keySet());

        Assert.assertEquals("Неочікувані дати у календарі: " + res, 0, res.size());
        for (LocalDateTime date : timeline.keySet()) {
            List<Task> resList = new ArrayList<>();
            for (Object o : result.get(date)) resList.add(new Task(o));
            if (!areEqual(timeline.get(date), resList))
                Assert.fail("Для " + date + ": " + getMessage());
        }
    }
}
