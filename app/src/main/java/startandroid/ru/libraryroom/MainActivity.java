package startandroid.ru.libraryroom;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText et_id, et_name, et_salary;
    Button btn_add, btn_delete, btn_get;
    ListView lv_employees;
    //List<Employee> listEmployee;
    AppDataBase db;
    EmployeeDao employeeDao;
    ListAdapter listAdapter;
    LayoutInflater layoutInflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_id = findViewById(R.id.et_id);
        et_name = findViewById(R.id.et_name);
        et_salary = findViewById(R.id.et_salary);
        btn_add = findViewById(R.id.btn_add);
        btn_delete = findViewById(R.id.btn_delete);
        btn_get = findViewById(R.id.btn_get);

        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_get.setOnClickListener(this);

        lv_employees = findViewById(R.id.lv_employees);

        db = App.getInstance().getDatabase();
        employeeDao = db.employeeDao();

        employeeDao.getAll()
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Employee>>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        //fillList(s.);
                    }

                    @Override
                    public void onNext(List<Employee> employees) {
                        //MainActivity.fillList(employees);
                        //Toast.makeText(MainActivity.this, "Id is empty!", Toast.LENGTH_SHORT).show();
                        //MainActivity.setId();
                        et_id.setText("100");
                    }

                    @Override
                    public void onError(Throwable t) {
                        et_id.setText("101");
                    }

                    @Override
                    public void onComplete() {
                        et_id.setText("102");
                    }

                    /*void fillList(List<Employee> employees) {
                        LayoutInflater layoutInflater = (LayoutInflater) MainActivity.getContex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        for (Employee employee : employees){
                            View view = layoutInflater.inflate(R.layout.item, lv_employees, false);
                            ((TextView) view.findViewById(R.id.tv_item_id)).setText((int) employee.id);
                            ((TextView) view.findViewById(R.id.tv_item_name)).setText(employee.name);
                            ((TextView) view.findViewById(R.id.tv_item_salary)).setText(employee.salary);
                            lv_employees.addView(view);
                        }

                    }*/
                });
        /*listAdapter = new ListAdapter(this, listEmployee);
        lv_employees = findViewById(R.id.lv_employees);
        lv_employees.setAdapter(listAdapter);*/

    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        String id_text = et_id.getText().toString();
        String name = et_name.getText().toString();
        String salary_text = et_salary.getText().toString();
        int id, salary;
        Employee look_for_employee;
        if (salary_text.isEmpty()){
            salary = 0;
        }else{
            salary = Integer.parseInt(salary_text);
        }
        switch (view.getId()){
            case R.id.btn_add:
                if (id_text.isEmpty()){
                    Toast.makeText(this, "Id is empty!", Toast.LENGTH_SHORT).show();
                    break;
                }
                id = Integer.parseInt(id_text);

                look_for_employee = employeeDao.getById(id);
                if (look_for_employee == null){
                    Employee employee = new Employee();
                    employee.id = id;
                    employee.name = name;
                    employee.salary = salary;
                    employeeDao.insert(employee);
                }else{
                    //look_for_employee.setData(name, salary);
                    look_for_employee.name = name;
                    look_for_employee.salary = salary;
                    employeeDao.update(look_for_employee);
                }
                break;
            case R.id.btn_delete:
                if (id_text.isEmpty()){
                    Toast.makeText(this, "Id is empty!", Toast.LENGTH_SHORT).show();
                    break;
                }
                id = Integer.parseInt(id_text);

                look_for_employee = employeeDao.getById(id);
                if (look_for_employee != null){
                    employeeDao.delete(look_for_employee);
                }
                break;
            case R.id.btn_get:
                if (id_text.isEmpty()){
                    /*List<Employee> ListEmployee = employeeDao.getAll();
                    for (Employee employee : ListEmployee){
                        et_id.setText("" + employee.id);
                        et_name.setText(employee.name);
                        et_salary.setText("" + employee.salary);
                        Toast.makeText(this, "Id = " + employee.id + "; name = " + employee.name + "; salary = " + employee.salary, Toast.LENGTH_SHORT).show();
                    }*/

                }else{
                    id = Integer.parseInt(id_text);
                    look_for_employee = employeeDao.getById(id);
                    if (look_for_employee == null) {
                        et_name.setText("");
                        et_salary.setText("");
                    }else{
                            et_name.setText(look_for_employee.name);
                            et_salary.setText("" + look_for_employee.salary);
                    }
                }
                break;
            default:
                break;
        }
    }

    /*public static void fillList(List<Employee> employees) {
        LayoutInflater layoutInflater = (LayoutInflater) MainActivity.getContex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (Employee employee : employees){
            View view = layoutInflater.inflate(R.layout.item, lv_employees, false);
            ((TextView) view.findViewById(R.id.tv_item_id)).setText((int) employee.id);
            ((TextView) view.findViewById(R.id.tv_item_name)).setText(employee.name);
            ((TextView) view.findViewById(R.id.tv_item_salary)).setText(employee.salary);
            lv_employees.addView(view);
        }

    }*/


}
