package ufc.learning.ListaContatos;

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
    private String dataContactList[]={"Pedro","Hannes","Bianca","Italo"};
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.btnAddContact=(Button) this.findViewById(R.id.btnAddContact);
        this.contactList=(ListView) this.findViewById(R.id.contactList);
        this.newContact=(EditText) this.findViewById(R.id.newContact);
        
        contactList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , dataContactList));
    }
    
    public void addContact(View v) {
        if(this.newContact.getTextSize()>0) {
            //TODO implement adding of contact
        }
    }
}