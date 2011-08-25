package ufc.learning.ListaContatos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ListaContatosActivity extends Activity {
    
    private Button btnAddContact;
    private ListView contactList;
    private EditText newContact;
    private String initDataContactList[]={"Pedro","Hannes","Momo","Italo","Charles","Laurent","Daniel"};
    private List<String> dataContactList=Arrays.asList(initDataContactList);
    
    private ArrayAdapter<String> arrayAdapter;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.btnAddContact=(Button) this.findViewById(R.id.btnAddContact);
        this.contactList=(ListView) this.findViewById(R.id.contactList);
        this.newContact=(EditText) this.findViewById(R.id.newContact);
        
        this.arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , dataContactList);
        
        this.contactList.setAdapter(arrayAdapter);
    }
    
    public void addContact(View v) {
        if(this.newContact.getTextSize()>0) {
            //if not empty, add a new contact
            this.dataContactList.add(this.newContact.getText().toString());
            //contactList.addView(newContact);
            this.arrayAdapter.notifyDataSetChanged();
        }
    }
}