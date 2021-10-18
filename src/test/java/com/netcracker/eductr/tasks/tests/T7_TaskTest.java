package com.netcracker.eductr.tasks.tests;

import com.netcracker.eductr.tasks.tests.model.Task;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static com.netcracker.eductr.tasks.tests.model.BaseCreator.isOld;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkClassExistence;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkMethodExistence;
import static com.netcracker.eductr.tasks.tests.utils.DatesTimes.*;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.ABSTRACT_LIST;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.TASK_BASE;
import static com.netcracker.eductr.tasks.tests.utils.Types.methodTypes.NEXT_TIME_AFTER;
import static com.netcracker.eductr.tasks.tests.model.TaskCreator.create;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T7_TaskTest {
    @BeforeClass
    public static void init() {
        Assume.assumeTrue(checkClassExistence(TASK_BASE));
        Assume.assumeTrue(checkMethodExistence(NEXT_TIME_AFTER, TASK_BASE));
        Assume.assumeTrue(checkClassExistence(ABSTRACT_LIST));
        Assume.assumeFalse(isOld());
    }

    @Test
    public void part1_testNextNonRepeative() {
        Task task = create("some", TODAY);
        task.setActive(true);
        Assert.assertEquals("nextTimeAfter(YESTERDAY) повернув неочікаву дату", TODAY, task.nextTimeAfter(YESTERDAY));
        Assert.assertEquals("nextTimeAfter(ALMOST_TODAY) повернув неочікаву дату", TODAY, task.nextTimeAfter(ALMOST_TODAY));
        Assert.assertNull("nextTimeAfter(TODAY) повернув неочікаву дату", task.nextTimeAfter(TODAY));
        Assert.assertNull("nextTimeAfter(TOMORROW) повернув неочікаву дату", task.nextTimeAfter(TOMORROW));
    }

    @Test
    public void part2_testNextRepeative() {
        Task task = create("some", TODAY, TOMORROW, 3600);
        task.setActive(true);
        Assert.assertEquals("nextTimeAfter(YESTERDAY) повернув неочікаву дату", TODAY, task.nextTimeAfter(YESTERDAY));
        Assert.assertEquals("nextTimeAfter(ALMOST_TODAY) повернув неочікаву дату", TODAY, task.nextTimeAfter(ALMOST_TODAY));
        Assert.assertEquals("nextTimeAfter(TODAY) повернув неочікаву дату", TODAY_1H, task.nextTimeAfter(TODAY));
        Assert.assertEquals("nextTimeAfter(TODAY_1H) повернув неочікаву дату", TODAY_2H, task.nextTimeAfter(TODAY_1H));
        Assert.assertEquals("nextTimeAfter(TODAY_2H) повернув неочікаву дату", TODAY_3H, task.nextTimeAfter(TODAY_2H));
        Assert.assertEquals("nextTimeAfter(ALMOST_TOMORROW) повернув неочікаву дату", TOMORROW, task.nextTimeAfter(ALMOST_TOMORROW));
        Assert.assertNull("nextTimeAfter(TOMORROW) повернув неочікаву дату", task.nextTimeAfter(TOMORROW));
    }

    @Test
    public void part3_testNextInactive() {
        Task task = create("some", TODAY);
        task.setActive(false);
        Assert.assertNull("nextTimeAfter(YESTERDAY) повернув неочікаву дату", task.nextTimeAfter(YESTERDAY));
    }
}
