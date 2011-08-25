package ufc.learning.ListaContatos;

import java.util.ArrayList;
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
    private List<Contact> listOfContacts= new ArrayList<Contact>();
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.btnAddContact=(Button) this.findViewById(R.id.btnAddContact);
        this.contactList=(ListView) this.findViewById(R.id.contactList);
        this.newContact=(EditText) this.findViewById(R.id.newContact);
        
        this.listOfContacts.add(new Contact("Test", "Truc", "test@gmail.com"));
        
        ContactListAdapter adapter=new ContactListAdapter(this, listOfContacts);
        
        this.contactList.setAdapter(adapter);
    }
    
    public void addContact(View v) {
        if(this.newContact.getTextSize()>0) {
            //TODO implement adding of contact
        }
    }
}