/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

/**Classe abstracta do tipo Entidade
 *
 * @author: Pedro Silva - 2007183130
 */
public abstract class Entidade {
    protected static int id_counter=0;
    protected int id;
    protected String cor;
    protected String forma;
    protected Coordenada coordenadas;
    
    public Entidade(String cor,String forma,int x,int y){
        id=id_counter++;
        this.cor=cor;
        this.forma=forma;
        coordenadas=new Coordenada(x,y);
    }
    
    public int getId(){
        return id;
    }
    
    public String getCor(){
        return cor;
    }
    
    public String getForma(){
        return forma;
    }
    
    public String toString(){
        return "\nId: "+id+"\nCor: "+cor+"\nForma: "+forma;
    }
}
