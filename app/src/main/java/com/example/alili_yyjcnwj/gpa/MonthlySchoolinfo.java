package com.example.alili_yyjcnwj.gpa;

import org.hyunjun.school.School;
import org.hyunjun.school.SchoolMenu;
import org.hyunjun.school.SchoolSchedule;

import java.util.List;

/**
 * Created by alili_yyjcnwj on 2017-10-07.
 */

public class MonthlySchoolinfo {
    private List<SchoolSchedule> mSchedule;
    private List<SchoolMenu> mMenu;

    private int mYear;
    private int mMonth;

    public MonthlySchoolinfo()
    {
        this.mYear = 2017;
        this.mMonth = 10;
    }

    public MonthlySchoolinfo(int mYear, int mMonth) {
        this.mYear = mYear;
        this.mMonth = mMonth;
    }

    public void clear()
    {
        mSchedule.clear();
        mMenu.clear();
    }

    public void setmSchedule(List<SchoolSchedule> schedule)
    {
        mSchedule = schedule;
    }
    public void setmMenu(List<SchoolMenu> menu)
    {
        mMenu = menu;
    }

    public String getSchedule(int i)
    {
        return mSchedule.get(i).schedule;
    }

    public String getMenuLunch(int i)
    {
        return mMenu.get(i).lunch;
    }
    public String getMenuDinner(int i)
    {
        return mMenu.get(i).dinner;
    }
}
