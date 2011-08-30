package ufc.learning.ExBasicoBD.model;

import android.content.ContentValues; 
import android.content.Context; 
import android.database.Cursor; 
import android.database.SQLException; 
import android.database.sqlite.SQLiteDatabase; 
import android.util.Log;

public class VeiculoDAO { 
    private static final String TAG_CLASSE = "VeiculoDAO"; 
    private String TAG_METODO; 
    private String comandoSQL; 
    private SQLiteDatabase objSQLiteDatabase; 
    public static final String NOME_BD = "bd_exbasicopersistenciabd"; 
    public static final String NOME_TABELA = "tb_veiculo"; 
    public static final int VERSAO_BD = 0; 
    public static final String CAMPO_ID = "_id"; 
    public static final String CAMPO_PLACA = "placa"; 
    public static final String CAMPO_TIPO = "tipo";

    public VeiculoDAO(Context objContexto){ 
        Log.v(TAG_CLASSE, "Abriu o BD? " + this.abrirBD(objContexto)); 
        Log.v(TAG_CLASSE, "Precisou criar estrutura do BD? " + this.criarEstruturaBD());
    }
    public boolean abrirBD(Context objContexto){ 
        boolean confirmacao = false;
        this.TAG_METODO = "_abrirBD()"; 
        try {
            this.objSQLiteDatabase = objContexto.openOrCreateDatabase(NOME_BD, Context.MODE_PRIVATE, null); 
            confirmacao = true; 
            Log.v(TAG_CLASSE+TAG_METODO, "Abriu o BD!");
        } 
        catch (SQLException excSQL) { 
            confirmacao = false;
        }
        Log.e(TAG_CLASSE+TAG_METODO, "Erro ao tentar abrir o BD!"); 
        return confirmacao;
    }
    public boolean criarEstruturaBD(){ 
        boolean confirmacao = false;
        this.TAG_METODO = "_criarEstruturaBD()"; 
        this.comandoSQL = "CREATE TABLE " + NOME_TABELA + "(" + CAMPO_ID + " integer primary key autoincrement, " + CAMPO_PLACA + " text not null," + CAMPO_TIPO + " text not null" + ");"; 
        try {
            this.objSQLiteDatabase.execSQL(this.comandoSQL); 
            confirmacao = true; Log.v(TAG_CLASSE+TAG_METODO, "Estrutura do BD foi criada!");
        } 
        catch (SQLException excSQL) { 
            Log.w(TAG_CLASSE+TAG_METODO, "A estrutura do BD ja tinha sido criada!");
        }
        return confirmacao;
    }
    public boolean fecharBD() { 
        boolean confirmacao = false;
        this.TAG_METODO = "_fecharBD()"; 
        this.objSQLiteDatabase.close(); 
        confirmacao = true; 
        Log.v(TAG_CLASSE+TAG_METODO, "Fechou BD!"); 
        return confirmacao;
    }
    public long inserirVeiculo(Veiculo objVeiculo){ 
        ContentValues objContentValues = new ContentValues(); 
        objContentValues.put("placa", objVeiculo.getPlaca()); 
        objContentValues.put("tipo", objVeiculo.getTipo()); 
        return (this.objSQLiteDatabase.insert(NOME_TABELA, null, objContentValues));
    }
    public boolean excluirTodosVeiculos(){ 
        boolean confirmacao = false;
        this.TAG_METODO = "_excluirTodosVeiculos()"; 
        this.comandoSQL = "DROP TABLE IF EXISTS " + NOME_TABELA; 
        try {
            Log.v(TAG_CLASSE+TAG_METODO, "Excluindo tabela do BD..."); 
            this.objSQLiteDatabase.execSQL(this.comandoSQL); 
            confirmacao = true; 
            Log.v(TAG_CLASSE+TAG_METODO, "Tabela excluida do BD...");
        } catch (SQLException excSQL) { // TODO: handle exception
            confirmacao = false; 
            Log.v(TAG_CLASSE+TAG_METODO, "Nao exclui tabela do BD!");
        } 
        return confirmacao;
    }
    public int excluirVeiculoPeloID(long id){ 
        String clausulaWHERE = CAMPO_ID + "=" + id; 
        return (this.objSQLiteDatabase.delete(NOME_TABELA, clausulaWHERE, null));
    }
    public Cursor consultarTodosVeiculos(){ 
        Cursor objCursorResultado = null; 
        String[] colunasConsulta = new String[] { "_id", "placa", "tipo" }; 
        this.TAG_METODO = "_consultarTodosVeiculos()"; 
        try {
            objCursorResultado = this.objSQLiteDatabase.query(NOME_TABELA, colunasConsulta, null, null, null, null, null); 
            Log.v(TAG_CLASSE+TAG_METODO,"Quant. de dados no BD: " + objCursorResultado.getCount());
        } 
        catch (Exception e) { 
            Log.e(TAG_CLASSE+TAG_METODO, "Erro ao executar consulta no BD: " + e.getMessage());
        } 
        return objCursorResultado;
    }
    public Cursor consultarVeiculoPeloID(long id){ 
        Cursor objCursorResultado = null; 
        String clausulaWHERE = CAMPO_ID + "=" + id; 
        String[] colunasConsulta = new String[] { "_id", "placa", "tipo" }; 
        this.TAG_METODO = "_consultarVeiculoPeloID()"; 
        try {
            objCursorResultado = this.objSQLiteDatabase.query(NOME_TABELA, colunasConsulta, clausulaWHERE, null, null, null, null); 
            Log.v(TAG_CLASSE+TAG_METODO, "Quant. de dados consultados pelo ID no BD: " + objCursorResultado.getCount());
        } 
        catch (Exception e) { 
            Log.e(TAG_CLASSE+TAG_METODO, "Erro ao executar consulta pelo ID no BD: " + e.getMessage());
        }
        return objCursorResultado;
    }

    public boolean verificarCadastroVeiculo(Veiculo objVeiculo){ 
        boolean confirmacao = false;
        Cursor objCursorResultado = null; 
        String clausulaWHERE = CAMPO_PLACA + "=" + objVeiculo.getPlaca(); 
        String[] colunasConsulta = new String[] { "_id", "placa", "tipo" }; 
        this.TAG_METODO = "_verificarCadastroVeiculo()"; 
        try {
            objCursorResultado = this.objSQLiteDatabase.query(NOME_TABELA, colunasConsulta, clausulaWHERE, null, null, null, null); 
            confirmacao = true; 
            Log.v(TAG_CLASSE+TAG_METODO, "Veiculo " + objVeiculo.getPlaca() + " ja cadastrado no BD!");
        } 
        catch (SQLException excSQL) { 
            confirmacao = false;
            Log.v(TAG_CLASSE+TAG_METODO, "Veiculo " + objVeiculo.getPlaca() + " ainda nao cadastrado no BD!");
        }
        return confirmacao;
    }
    public int atualizarVeiculoPeloID(long id, String placa, String tipo) { 
        String clausulaWHERE = CAMPO_ID + "=" + id; 
        ContentValues objContentValues = new ContentValues(); 
        objContentValues.put(CAMPO_PLACA, placa); 
        objContentValues.put(CAMPO_TIPO, tipo);
        return (this.objSQLiteDatabase.update(NOME_TABELA, objContentValues, clausulaWHERE, null));
    }
    
    public String[] obterNomesBDAssociados(Context objContexto) {
        return objContexto.databaseList(); 
    }
    
    public boolean excluirBDAssociado(Context objContext, String nomeBD) {
        return objContext.deleteDatabase(nomeBD);
    }
    
    public int excluirVeiculoPelaPlaca(String placa) {
        String clausulaWHERE = CAMPO_PLACA + "='" + placa+ "'"; 
        return (this.objSQLiteDatabase.delete(NOME_TABELA, clausulaWHERE, null));
    }
    
    public int excluirVeiculoPeloTipo(String tipo) {
        String clausulaWHERE = CAMPO_TIPO + "='" + tipo+ "'"; 
        return (this.objSQLiteDatabase.delete(NOME_TABELA, clausulaWHERE, null));
    }
    
    public Cursor consultarVeiculoPeloTipo(String tipo) {
        Cursor objCursorResultado = null; 
        String clausulaWHERE = CAMPO_TIPO + "='" + tipo+ "'"; 
        String[] colunasConsulta = new String[] { "_id", "placa", "tipo" }; 
        this.TAG_METODO = "_consultarVeiculoPeloTipo()"; 
        try {
            objCursorResultado = this.objSQLiteDatabase.query(NOME_TABELA, colunasConsulta, clausulaWHERE, null, null, null, null); 
            Log.v(TAG_CLASSE+TAG_METODO, "Quant. de dados consultados pelo Tipo no BD: " + objCursorResultado.getCount());
        } 
        catch (Exception e) { 
            Log.e(TAG_CLASSE+TAG_METODO, "Erro ao executar consulta pelo Tipo no BD: " + e.getMessage());
        }
        return objCursorResultado;
    }
    
    public Cursor consultarVeiculoPelaPlaca(String placa) {
        Cursor objCursorResultado = null; 
        String clausulaWHERE = CAMPO_PLACA + "='" + placa+ "'"; 
        String[] colunasConsulta = new String[] { "_id", "placa", "tipo" }; 
        this.TAG_METODO = "_consultarVeiculoPeloPlaca()"; 
        try {
            objCursorResultado = this.objSQLiteDatabase.query(NOME_TABELA, colunasConsulta, clausulaWHERE, null, null, null, null); 
            Log.v(TAG_CLASSE+TAG_METODO, "Quant. de dados consultados pelo Placa no BD: " + objCursorResultado.getCount());
        } 
        catch (Exception e) { 
            Log.e(TAG_CLASSE+TAG_METODO, "Erro ao executar consulta pelo Placa no BD: " + e.getMessage());
        }
        return objCursorResultado;
    }
    
    public int atualizarVeiculoPelaPlaca(String placa,String newPlaca, String tipo) {
        String clausulaWHERE = CAMPO_PLACA + "='" + placa+ "'"; 
        ContentValues objContentValues = new ContentValues(); 
        objContentValues.put(CAMPO_PLACA, newPlaca); 
        objContentValues.put(CAMPO_TIPO, tipo);
        return (this.objSQLiteDatabase.update(NOME_TABELA, objContentValues, clausulaWHERE, null));
    }
}
