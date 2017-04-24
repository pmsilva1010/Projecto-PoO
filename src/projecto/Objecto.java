/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

/**Classe para objectos do tipo Objecto
 *
 * @author: Pedro Silva - 2007183130
 */
public class Objecto extends Entidade{
    private String tipo;
    
    public Objecto(String cor,String forma,int x,int y,String tipo){
        super(cor,forma,x,y);
        this.tipo=tipo;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public String toString(){
        return "\nObjecto Inanimado"+super.toString()+"\nTipo: "+tipo+"\nPosição: ["+coordenadas.getX()+","+coordenadas.getY()+"]\n";
    }
}
