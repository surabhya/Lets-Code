package helpers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import models.Marriage;
import views.controllers.MarriageApplication;

/**
 * Created by saryal on 12/22/15.
 */
public class ViewHelper extends AppCompatActivity {
    protected Marriage mainMarriage;
    DatabaseHelper dbHelper = new DatabaseHelper(this);

    protected int getLayoutId(){
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mainMarriage = ((MarriageApplication) getApplication()).getMainMarriage();

    }

    protected void setTextValue(int Id, int text) {
        setTextValue(Id, text + "");
    }

    protected void setRadio(int id){
        RadioButton rdbtn = (RadioButton) findViewById(id);
        rdbtn.setChecked(true);
    }
    protected void setTextValue(int Id, String text) {
        EditText edit = (EditText) findViewById(Id);
        edit.setText(text, TextView.BufferType.EDITABLE);
    }

    protected String getTextValue(int Id){
        return ((EditText)findViewById(Id)).getText().toString();
    }

    protected void setSpinnerValue(int Id, Object val) {
        Spinner spinner = (Spinner) findViewById(Id);
        SpinnerAdapter adapt = spinner.getAdapter();
        for (int position = 0; position < adapt.getCount(); position++) {
            if (adapt.getItem(position).equals(val)) {
                spinner.setSelection(position);
                return;
            }
        }
    }

}
