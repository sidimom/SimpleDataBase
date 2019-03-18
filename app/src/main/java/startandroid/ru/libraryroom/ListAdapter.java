package startandroid.ru.libraryroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 06.03.2019.
 */

public class ListAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater layoutInflater;
    List<Employee> employeeList;

    ListAdapter(Context contex, List<Employee> listOfEmployee){
        ctx = contex;
        employeeList = listOfEmployee;
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return employeeList.size();
    }

    @Override
    public Object getItem(int i) {
        return employeeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = layoutInflater.inflate(R.layout.item, viewGroup, false);
        }

        Employee itemListEmployee = (Employee) getItem(i);
        ((TextView) view.findViewById(R.id.tv_item_id)).setText((int) itemListEmployee.id);
        ((TextView) view.findViewById(R.id.tv_item_name)).setText(itemListEmployee.name);
        ((TextView) view.findViewById(R.id.tv_item_salary)).setText(itemListEmployee.salary);
        return view;
    }
}
