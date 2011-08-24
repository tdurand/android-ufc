package ufc.learning.conversoesmonetarias;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ConversoesMonetariasActivity extends Activity {
    
    Button btConvert;
    EditText amount;
    TextView result;
    RadioGroup listChoice;
    RadioButton currentChoice;
    RadioButton euroreal;
    RadioButton realeuro;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //gather views elements
        this.btConvert=(Button) this.findViewById(R.id.btnConvert);
        this.amount=(EditText) this.findViewById(R.id.amount);
        this.result=(TextView) this.findViewById(R.id.result);
        this.listChoice=(RadioGroup) this.findViewById(R.id.conversionType);
        this.euroreal=(RadioButton) this.findViewById(R.id.euroReal);
        this.realeuro=(RadioButton) this.findViewById(R.id.realEuro);
        
    }
    //handling the click of convert button
    public void convert(View view) {
        String amountToConvertString=this.amount.getText().toString();
        Float amountToConvert=Float.valueOf(amountToConvertString);
        Float result=null;
        
        this.currentChoice=(RadioButton) this.findViewById(this.listChoice.getCheckedRadioButtonId());
        
        if(this.currentChoice.equals(this.euroreal)) {
            result=(float) (amountToConvert*2.3);
        }
        else {
            result=(float) (amountToConvert/2.3);
        }
        
        this.result.setText(result.toString());
    }
}