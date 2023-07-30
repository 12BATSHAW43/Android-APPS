package com.example.simple_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialButton add,sub,mul,div,equals_btn,num0,num1,num2,num3,num4,num5,num6,num7,num8,num9,period,left_bracket,right_bracket,AC_btn,backspace;
    TextView result_tv, solution_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_tv=findViewById(R.id.result_tv);
        solution_tv=findViewById(R.id.solution_tv);
        setID(left_bracket,R.id.left_bracket_btn);
        setID(right_bracket,R.id.right_bracket_btn);
        setID(backspace,R.id.backspace_btn);
        setID(div,R.id.divide_btn);
        setID(add,R.id.addition_btn);
        setID(sub,R.id.subtraction_btn);
        setID(mul,R.id.multiply_btn);
        setID(equals_btn,R.id.result_btn);
        setID(period,R.id.period_dot_btn);
        setID(AC_btn,R.id.AC_btn);
        setID(num9,R.id.num_9);
        setID(num8,R.id.num_8);
        setID(num7,R.id.num_7);
        setID(num6,R.id.num_6);
        setID(num5,R.id.num_5);
        setID(num4,R.id.num_4);
        setID(num3,R.id.num_3);
        setID(num2,R.id.num_2);
        setID(num1,R.id.num_1);
        setID(num0,R.id.num_0);


    }//end of onCreate()

    void setID(MaterialButton btn,int id)
    {
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }//end of setID

    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_tv.getText().toString();

        if(buttonText.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        }

        if(buttonText.equals("C") ) {
            //backspace btn
            if(result_tv.getText().length()==1)
            {
                result_tv.setText("0");
                solution_tv.setText("");
                return;
            }
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        }
        else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solution_tv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            result_tv.setText(finalResult);
        }

    }//end of onCLick method

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
                //context.evaluateString() will calculate our desired output
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
                //this is used to remove zero after decimal if it ends with just single zero after decimal
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }//end of getResult
}