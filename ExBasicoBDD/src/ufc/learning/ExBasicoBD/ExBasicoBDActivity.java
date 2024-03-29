package ufc.learning.ExBasicoBD;

import ufc.learning.ExBasicoBD.model.Veiculo;
import ufc.learning.ExBasicoBD.model.VeiculoDAO;
import android.app.Activity; 
import android.content.Context;
import android.database.Cursor; 
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ExBasicoBDActivity extends Activity { 
    private static final String TAG_CLASSE = "ExBasicoBDActivity"; 
    private TextView tvInfos;
    private Veiculo objVeiculo01, objVeiculo02, objVeiculo03; 
    private VeiculoDAO objVeiculoDAO;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.main); 
        this.inicializarAtributos(); 
        this.realizarTestes();
    }
    private void associarComponentesView() {
        this.tvInfos = (TextView) this.findViewById(R.id.tvInfos);
    }
    private void inicializarAtributos() { 
        this.associarComponentesView(); 
        this.objVeiculoDAO = new VeiculoDAO(this); 
        this.objVeiculo01 = new Veiculo("HUJ7654", "MOTO"); 
        this.objVeiculo02 = new Veiculo("NJM1234", "CARRO"); 
        this.objVeiculo03 = new Veiculo("KLJ8765", "MOTO");
    }
    private void realizarTestes() { 
        this.testarExclusaoTodos();
        this.testarInsercao(); 
        this.testarConsultaTodos(); 
        this.testarConsultaPeloID(2); 
        this.testarConsultaPeloID(1); 
        this.testarConsultaPeloID(3); 
        this.testarConsultaPelaPlaca("HUJ7654");
        this.testarConsultaPeloTipo("MOTO");
        this.testarAtualizacaoPeloID(3, "NJM1234", "MOTO"); 
        this.testarAtualizacaoPeloID(2, "KLJ8765", "CARRO"); 
        this.testarAtualizacaoPeloID(1, "HUJ7654", "CARRO"); 
        this.testarAtualizacaoPelaPlaca("KLJ8765", "Test", "MOTO");
        this.testarConsultaTodos();
        this.testarExclusaoPelaPlaca("NJM1234");
        this.testarExclusaoPeloTipo("CARRO");
        this.testarExclusaoPorID(2); 
        this.testarExclusaoPorID(1); 
        this.testarExclusaoPorID(3); 
        this.testarConsultaTodos();
        this.testarFechamentoBD();
        
        //Listing database
        this.testarObterNomesBDAssociados(this);
        //Try to delete one
        this.testarExcluirBDAssociado(this,"bd_exbasicopersistenciabd");
        //Listing database
        this.testarObterNomesBDAssociados(this);
    } 
    private void testarExclusaoTodos() {
        Log.v(TAG_CLASSE, "Excluiu tabela do BD? " + this.objVeiculoDAO.excluirTodosVeiculos()); 
        Log.v(TAG_CLASSE, "Remontou tabela do BD? " + this.objVeiculoDAO.criarEstruturaBD());
    }
    private void testarInsercao() { 
        long id01, id02, id03;
        if(this.objVeiculoDAO.verificarCadastroVeiculo(this.objVeiculo01)){ 
            Log.v(TAG_CLASSE, "Veiculo " + this.objVeiculo01.getPlaca() + " ja cadastrado no BD!");
        }
        else {
            id01 = this.objVeiculoDAO.inserirVeiculo(this.objVeiculo01); 
            if( id01 > 0){
                Log.v(TAG_CLASSE, "Inseriu veiculo " + this.objVeiculo01.getPlaca() + " com ID = " + id01);
            }
            else { 
                Log.e(TAG_CLASSE, "Nao inseriu veiculo " + this.objVeiculo01.getPlaca() + "!");
            }
        }
        if(this.objVeiculoDAO.verificarCadastroVeiculo(this.objVeiculo02)){
            Log.v(TAG_CLASSE, "Veiculo " + this.objVeiculo02.getPlaca() + " ja cadastrado no BD!");
        }
        else {
            id02 = this.objVeiculoDAO.inserirVeiculo(this.objVeiculo02); 
            if( id02 > 0){
                Log.v(TAG_CLASSE,"Inseriu veiculo " + this.objVeiculo02.getPlaca() + " com ID = " + id02);
            }
            else {
                Log.e(TAG_CLASSE, "Nao inseriu veiculo " + this.objVeiculo01.getPlaca() + "!");
            }
            if(this.objVeiculoDAO.verificarCadastroVeiculo(this.objVeiculo03)){
                Log.v(TAG_CLASSE, "Veiculo " + this.objVeiculo03.getPlaca() + " ja cadastrado no BD!");
            }
            else {
                id03 = this.objVeiculoDAO.inserirVeiculo(this.objVeiculo03);
                if( id03 > 0){
                    Log.v(TAG_CLASSE, "Inseriu veiculo " + this.objVeiculo03.getPlaca() + " com ID = " + id03);
                }
                else {
                    Log.e(TAG_CLASSE, "Nao inseriu veiculo " + this.objVeiculo01.getPlaca() + "!");
                }
            }
        }
    }
    
    private void testarConsultaTodos() { 
        StringBuilder objStringBuilder = new StringBuilder(); 
        Cursor objCursorResultado = this.objVeiculoDAO.consultarTodosVeiculos(); 
        objStringBuilder.append("Quant. de veíc. cadast. no BD = " + objCursorResultado.getCount()); 
        objCursorResultado.moveToFirst(); 
        for (int i = 0; i < objCursorResultado.getCount(); i++) {
            objStringBuilder.append("\n" + objCursorResultado.getLong(0) + " | " + objCursorResultado.getString(1) + " | " + objCursorResultado.getString(2)); 
            objCursorResultado.moveToNext();
        }
        this.tvInfos.setText(objStringBuilder);
    }
    
    private void testarConsultaPeloID(long id) { 
        StringBuilder objStringBuilder = new StringBuilder(); 
        Cursor objCursorResultado = this.objVeiculoDAO.consultarVeiculoPeloID(id); 
        objStringBuilder.append("Quant. de veíc. cadast. consultados pelo id no BD = " + objCursorResultado.getCount()); 
        objCursorResultado.moveToFirst(); 
        for (int i = 0; i < objCursorResultado.getCount(); i++) {
            objStringBuilder.append("\n" + objCursorResultado.getLong(0) + " | " + objCursorResultado.getString(1) + " | " +objCursorResultado.getString(2)); 
            objCursorResultado.moveToNext();
        }
        this.tvInfos.setText(objStringBuilder);
    }
    
    private void testarAtualizacaoPeloID(long id, String placa, String tipo) { 
        if(this.objVeiculoDAO.atualizarVeiculoPeloID(id, placa, tipo) > 0){
            Log.v(TAG_CLASSE, "Atualizou veiculo com placa = " + placa + " pelo ID no BD!");
        }
        else {
            Log.e(TAG_CLASSE, "Não atualizou veiculo com placa = " + placa + " pelo ID no BD!");
        }
    }
    
    private void testarExclusaoPorID(long id) { 
        if(this.objVeiculoDAO.excluirVeiculoPeloID(id) > 0){
            Log.v(TAG_CLASSE, "Excluiu veiculo com id = " + id + " do BD!");
        } 
        else { 
            Log.e(TAG_CLASSE, "Nao excluiu veiculo com id = " + id + " do BD!");
        }
    }
    private void testarFechamentoBD() {
        Log.v(TAG_CLASSE, "Fechou BD? " + this.objVeiculoDAO.fecharBD());
    }
    
    private void testarObterNomesBDAssociados(Context objContexto) {
        String[] nomesAssociados=this.objVeiculoDAO.obterNomesBDAssociados(objContexto);
        for (int i = 0; i < nomesAssociados.length; i++) {
            Log.v(TAG_CLASSE, "List of databases associated :" + nomesAssociados[i]);
        }
    }
    
    private void testarExcluirBDAssociado(Context objContexto, String nomeBD) {
       Log.v(TAG_CLASSE, "Try to delete database: " +nomeBD+ " return:" + this.objVeiculoDAO.excluirBDAssociado(objContexto, nomeBD));
    }
    
    private void testarExclusaoPelaPlaca(String placa) {
        int result=this.objVeiculoDAO.excluirVeiculoPelaPlaca(placa);
        if(result > 0){
            Log.v(TAG_CLASSE, "Excluiu "+result+" veiculo(s) com placa="+placa +" do BD!");
        } 
        else { 
            Log.e(TAG_CLASSE, "Nao excluiu veiculo com placa = " + placa + " do BD!");
        }
    }
    
    private void testarExclusaoPeloTipo(String tipo) {
        int result=this.objVeiculoDAO.excluirVeiculoPeloTipo(tipo);
        if(result > 0){
            Log.v(TAG_CLASSE, "Excluiu "+result+" veiculo(s) com tipo="+tipo +" do BD!");
        } 
        else { 
            Log.e(TAG_CLASSE, "Nao excluiu veiculo com tipo = " + tipo + " do BD!");
        }
    }
    
    private void testarConsultaPelaPlaca(String placa) {
        StringBuilder objStringBuilder = new StringBuilder(); 
        Cursor objCursorResultado = this.objVeiculoDAO.consultarVeiculoPelaPlaca(placa); 
        objStringBuilder.append("Quant. de veíc. cadast. consultados pela placa no BD = " + objCursorResultado.getCount()); 
        objCursorResultado.moveToFirst(); 
        for (int i = 0; i < objCursorResultado.getCount(); i++) {
            objStringBuilder.append("\n" + objCursorResultado.getLong(0) + " | " + objCursorResultado.getString(1) + " | " +objCursorResultado.getString(2)); 
            objCursorResultado.moveToNext();
        }
        this.tvInfos.setText(objStringBuilder);
    }
    
    private void testarConsultaPeloTipo(String tipo) {
        StringBuilder objStringBuilder = new StringBuilder(); 
        Cursor objCursorResultado = this.objVeiculoDAO.consultarVeiculoPeloTipo(tipo); 
        objStringBuilder.append("Quant. de veíc. cadast. consultados pelo tipo no BD = " + objCursorResultado.getCount()); 
        objCursorResultado.moveToFirst(); 
        for (int i = 0; i < objCursorResultado.getCount(); i++) {
            objStringBuilder.append("\n" + objCursorResultado.getLong(0) + " | " + objCursorResultado.getString(1) + " | " +objCursorResultado.getString(2)); 
            objCursorResultado.moveToNext();
        }
        this.tvInfos.setText(objStringBuilder);
    }
    
    private void testarAtualizacaoPelaPlaca(String placa,String newPlaca, String tipo) {
        int result=this.objVeiculoDAO.atualizarVeiculoPelaPlaca(placa,newPlaca, tipo);
        if(result > 0){
            Log.v(TAG_CLASSE, "Atualizou "+ result+" veiculo(s) com placa = " + placa + " no BD!");
        }
        else {
            Log.e(TAG_CLASSE, "Não atualizou veiculo com placa = " + placa + " no BD!");
        }
    }
}