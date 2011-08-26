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
    private ContactListAdapter adapter;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.btnAddContact=(Button) this.findViewById(R.id.btnAddContact);
        this.contactList=(ListView) this.findViewById(R.id.contactList);
        this.newContact=(EditText) this.findViewById(R.id.newContact);
        
        this.listOfContacts.add(new Contact("Italo", "8888999", "test@gmail.com"));
        this.listOfContacts.add(new Contact("Charles", "8888999", "test@gmail.com"));
        this.listOfContacts.add(new Contact("Laurent", "8888999", "test@gmail.com"));
        this.listOfContacts.add(new Contact("Vanessa", "8888999", "test@gmail.com"));
        this.listOfContacts.add(new Contact("Yannic", "8888999", "test@gmail.com"));
        this.listOfContacts.add(new Contact("Tibo", "8888999", "test@gmail.com"));
        this.listOfContacts.add(new Contact("Marvin", "8888999", "test@gmail.com"));
        this.listOfContacts.add(new Contact("Daniel", "8888999", "test@gmail.com"));
        this.listOfContacts.add(new Contact("Paulo", "8888999", "test@gmail.com"));
        
        adapter=new ContactListAdapter(this, listOfContacts);
        
        this.contactList.setAdapter(adapter);
    }
    
    public void addContact(View v) {
        if(!this.newContact.getText().toString().equals("")) {
            this.listOfContacts.add(new Contact(this.newContact.getText().toString(),"8888999","test@gmail.com"));
            this.adapter.notifyDataSetChanged();
            this.newContact.setText("");
        }
    }
    
    public void removeContact(View v) {
        Contact entry = (Contact) v.getTag();
        listOfContacts.remove(entry);
        this.adapter.notifyDataSetChanged();
    }
}