package com.project.storage;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.project.storage.bean.Gender;
import com.project.storage.bean.StudentBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.storage.Storage;
import io.storage.callback.FindKeysCallback;
import io.storage.callback.MassDeleteCallback;
import io.storage.callback.ReadArrayCallback;
import io.storage.callback.ReadCallback;
import io.storage.callback.WriteCallback;

public class MainActivity extends Activity {
    private List<StudentBean> students = new ArrayList<>();
    private StudentBean studentJack;
    private StudentBean studentJames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentJack = new StudentBean();
        studentJack.setCardBalance(new BigDecimal(9.1212111102233));
        studentJack.setBirthday(new Date(701857136000L));
        studentJack.setCredit(-9.2F);
        studentJack.setValid(true);
        studentJack.setId(100000001L);
        studentJack.setName("Jack Mao 😯");
        studentJack.setGender(Gender.MALE);
        studentJack.setGrade(12);
        students.add(studentJack);


        studentJames = new StudentBean();
        studentJames.setCardBalance(new BigDecimal(0.0000));
        studentJames.setBirthday(new Date(600857136000L));
        studentJames.setValid(true);
        studentJames.setId(100000002L);
        studentJames.setName("LeBron James 😄");
        studentJames.setGender(Gender.MALE);
        students.add(studentJames);

        StudentBean student = new StudentBean();

        student.setCardBalance(new BigDecimal("-3.33"));
        student.setBirthday(new Date(401857136000L));
        student.setCredit(100F);
        student.setValid(false);
        student.setId(100000003L);
        student.setName("\t\n\r\\\\");
        student.setGender(Gender.FEMALE);
        student.setGrade(0);
        students.add(student);

        student = new StudentBean();
        student.setCardBalance(new BigDecimal(199244.21));
        student.setBirthday(new Date(801857136000L));
        student.setCredit(0.5F);
        student.setValid(true);
        student.setId(100000004L);
        student.setName("Dwayne Wade");
        student.setGrade(10);
        students.add(student);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnMainDatabase) {
            Storage.get(getActivity()).writeInDatabase("stu_jack", studentJack);
            Storage.get(getActivity()).writeInDatabase("stu_james", studentJames);
            Storage.get(getActivity()).writeInDatabase("test_1", 1);
            Storage.get(getActivity()).writeInDatabase("test_2", 2.0f);
            Storage.get(getActivity()).writeInDatabase("test_3", "3");
            Storage.get(getActivity()).writeInDatabase("test_4", false);

            Storage.get(getActivity()).readStringFromDatabase("stu_jack");
            Storage.get(getActivity()).readIntFromDatabase("test_1", 0);
            Storage.get(getActivity()).readDoubleFromDatabase("test_2", 0);
            Storage.get(getActivity()).readStringFromDatabase("test_3", "");
            Storage.get(getActivity()).readBooleanFromDatabase("test_4", false);

            StudentBean studentTemp = Storage.get(getActivity()).readFromDatabase("stu_jack", StudentBean.class);
            Log.i("studentJack", studentTemp.toString());

            Storage.get(getActivity()).readFromDatabaseAsync("stu_james", StudentBean.class, new ReadCallback<StudentBean>() {
                @Override
                public void onResult(boolean success, StudentBean result) {
                    Log.i("studentJames", result.toString());
                }
            });

            Storage.get(getActivity()).writeInDatabaseAsync("students", students, new WriteCallback() {
                @Override
                public void onResult(boolean success) {
                    Storage.get(getActivity()).readArrayFromDatabaseAsync("students", StudentBean.class, new ReadArrayCallback<StudentBean>() {
                        @Override
                        public void onResult(boolean success, StudentBean[] result) {
                            if (success) {
                                List<StudentBean> students = Arrays.asList(result);
                                Log.i("students", students.size() + " student");
                                for (StudentBean stu : students) {
                                    Log.i("student", stu.toString());
                                }
                            }
                        }
                    });
                }
            });

            Log.i("students exist", Storage.get(getActivity()).keyExist("students") + "");

            Storage.get(getActivity()).deleteFromDatabase("test_1");
            Log.i("test exist", Storage.get(getActivity()).keyExist("test_1") + "");

            Storage.get(getActivity()).findKeysByPrefix("test", new FindKeysCallback() {
                @Override
                public void onResult(String[] keys) {
                    Storage.get(getActivity()).massDeleteFromDatabaseAsync(keys, new MassDeleteCallback() {
                        @Override
                        public void onResult(boolean b) {
                            Log.i("test exist", Storage.get(getActivity()).keyExist("test_1") + "");
                        }
                    });
                }
            });

        } else if (v.getId() == R.id.btnMainMemoryCache) {
            // not weak ref
            Storage.get(this).writeInMemory("studentJack", studentJack, true);
            // weak ref
            Storage.get(this).writeInMemory("studentJames", studentJames);

            StudentBean jack = Storage.get(this).readFromMemory("studentJack", true);
            if (jack != null)
                Log.i("jack", jack.toString());
            StudentBean james = Storage.get(this).readFromMemory("studentJames");
            if (james != null)
                Log.i("james", james.toString());

            Storage.get(this).deleteFromMemory("studentJames");

            Storage.get(this).writeInMemory("Curry", "Chef Curry!!!");
            Storage.get(this).writeInMemory("Curry", null);
            Storage.get(this).writeInMemory("Curry", null);
            Object curry = Storage.get(this).readFromMemory("Curry");
            if (curry != null) {
                Log.i("curry", curry.toString());
            } else {
                Log.i("curry", "null");
            }
        }
    }

    private Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}