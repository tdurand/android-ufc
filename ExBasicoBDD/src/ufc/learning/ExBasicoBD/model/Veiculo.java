package ufc.learning.ExBasicoBD.model;

public class Veiculo { 
    private long _id;
    private String placa;
    private String tipo;
    public Veiculo() { super();
    }
    public Veiculo(String placa, String tipo) { super();
    this.placa = placa; this.tipo = tipo;
    }
    public Veiculo(long id, String placa, String tipo) { super();
    _id = id; this.placa = placa; this.tipo = tipo;
    }
    public long get_id() {
        return _id;
    }
    public void set_id(long _id) {
        this._id = _id;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    } 

}