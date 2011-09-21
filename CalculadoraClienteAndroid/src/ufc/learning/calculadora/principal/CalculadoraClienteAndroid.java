package ufc.learning.calculadora.principal;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CalculadoraClienteAndroid extends Activity {
    private static final String CATEGORIA = "Lab#03 - CMOVEL_2010.II";
    private static final String IP = "10.0.2.2";
    private static final int PORTA = 9000;
    private TextView tvResultado;
    private EditText etPrimeiroNumero, etSegundoNumero;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main);
        this.associarComponentesGraficos();
    }
    public void associarComponentesGraficos(){
        this.etPrimeiroNumero = (EditText) this.findViewById(R.id.etPrimeiroNumero);
        this.etSegundoNumero = (EditText) this.findViewById(R.id.etSegundoNumero);
        this.tvResultado = (TextView) this.findViewById(R.id.tvResultado);
    }

    // ---- HANDLERs Click buttons ---------
    public void somar(View view) {
        float n1 = Float.parseFloat(this.etPrimeiroNumero.getText().toString());
        float n2 = Float.parseFloat(this.etSegundoNumero.getText().toString());
        
        String result=sendRequest("somar", n1, n2);
        printResult("soma", result);
    }
    
    public void multiplicar(View view) {
        float n1 = Float.parseFloat(this.etPrimeiroNumero.getText().toString());
        float n2 = Float.parseFloat(this.etSegundoNumero.getText().toString());
        
        String result=sendRequest("multiplicar", n1, n2);
        printResult("multiplicação", result);
    }
    
    public void substrair(View view) {
        float n1 = Float.parseFloat(this.etPrimeiroNumero.getText().toString());
        float n2 = Float.parseFloat(this.etSegundoNumero.getText().toString());
        
        String result=sendRequest("substrair", n1, n2);
        printResult("subtração", result);
    }
    
    public void dividir(View view) {
        float n1 = Float.parseFloat(this.etPrimeiroNumero.getText().toString());
        float n2 = Float.parseFloat(this.etSegundoNumero.getText().toString());
        
        String result=sendRequest("dividir", n1, n2);
        printResult("divisão", result);
    }
    
    // Display result into the textedit
    public void printResult(String type,String result) {
        String texto = type+ " : " + result;
        this.tvResultado.setText(texto);
        Log.i(CATEGORIA, texto);
        this.tvResultado.setVisibility(View.VISIBLE);
    }
    
    // Construct the web url of the right webservice and call it : return the result
    public String sendRequest(String mode,Float n1,Float n2) {
        Log.i(CATEGORIA, "Enviando numeros... " + n1 + " + " + n2);
        //Http request
        String result=queryRESTurl("http://"+IP+":"+PORTA+"/"+mode+"/"+n1+"/"+n2);
        Log.i(CATEGORIA, "result: " + result);
        return result;
    }
    
    // Call the webservice
    public String queryRESTurl(String url) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        HttpResponse response;

        try {
            response = httpclient.execute(httpget);
            Log.i(CATEGORIA,"Status:[" + response.getStatusLine().toString() + "]");
            HttpEntity entity = response.getEntity();

            if (entity != null) {

                InputStream instream = entity.getContent();
                String result=parseISToString(instream);
                Log.i(CATEGORIA, "Result of converstion: [" + result + "]");

                instream.close();
                return result;
            }
        } catch (ClientProtocolException e) {
            Log.e("REST", "There was a protocol based error", e);
        } catch (IOException e) {
            Log.e("REST", "There was an IO Stream related error", e);
        }

        return null;
    }

    // Parse the result of the webservice
    public String parseISToString(java.io.InputStream is){
        java.io.DataInputStream din = new java.io.DataInputStream(is);
        StringBuffer sb = new StringBuffer();
        try{
            String line = null;
            while((line=din.readLine()) != null){
                sb.append(line+"\n");
            }
        }catch(Exception ex){
            ex.getMessage();
        }finally{
            try{
                is.close();
            }catch(Exception ex){}
        }
        return sb.toString();
    }


}