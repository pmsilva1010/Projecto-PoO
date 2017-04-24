/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

import java.util.*;

/**Classe abstracta do tipo Agente
 *
 * @author: Pedro Silva - 2007183130
 */
public abstract class Agente extends Entidade{
    protected ArrayList<Objecto> lista_objectos=new ArrayList<Objecto>();
    protected ArrayList<Objecto> lista_cvisao;
    protected ArrayList<Objecto> total_cvisao=new ArrayList<Objecto>();
    protected ArrayList<Coordenada> percurso=new ArrayList<Coordenada>();
    
    protected int cvisao;
    protected double distancia_percorrida;
    protected int n_objc_apreendidos;
    protected int n_objc_diferentes;
    
    public Agente(String cor,String forma,int x,int y,int cvisao){
        super(cor,forma,x,y);
        this.cvisao=cvisao;
        percurso.add(coordenadas);
    }
    
    public abstract void estrategia(ArrayList<Objecto> objectos,int xm,int ym);
    
    /**Método que faz um agente mover-se aleatóriamente para uma célula no
     * limite do seu campo de visão.
     * Calcula os minimos e os máximos possiveis para x e y.
     * Calcula um x aleatório, se x for um limite máximo ou mínimo calcula um y
     * aleatório. Se x for diferente dos limites máximos ou minimos o valor de y
     * passa a ser aleatóriamente o máximo ou minimo possivel.
     * Caso os valores ultrapassem os limites da matriz os valores são adptados
     * para os limits da matriz.
     * 
     * @param xm - Valor máximo de x na matriz
     * @param ym - Valor máximo de y na matriz
     */
    protected void movimento_aleatorio(int xm,int ym){
        Random num=new Random();
        int x,y;
        int minimo_x=coordenadas.getX()-cvisao,maximo_x=coordenadas.getX()+cvisao;
        int minimo_y=coordenadas.getY()-cvisao,maximo_y=coordenadas.getY()+cvisao;
        
        x=num.nextInt((maximo_x-minimo_x)+1)+minimo_x;
        
        if(x==minimo_x || x==maximo_x){
            y=num.nextInt((maximo_y-minimo_y)+1)+minimo_y;
        }
        else{
            if(num.nextInt(2)==0)
                y=minimo_y;
            else
                y=maximo_y;
        }
        
        if(x>xm)
            x=xm;
        else if(x<0)
            x=0;
        if (y>ym)
            y=ym;
        else if(y<0)
            y=0;
        
        coordenadas=new Coordenada(x,y);
    }
    
    /**Método para calcular a distancia entre o agente e um objecto.
     * Converte os valores recebidos em double.
     * Usa a fórmula: raiz quadrada((x1-x2)^2+(y1-y2)^2)).
     * 
     * @param a - Valor de x1
     * @param b - Valor de y1
     * @param c - Valor de x2
     * @param d - Valor de y2
     * @return distancia - Distancia entre o agente e o objecto
     */
    protected double distancia(int a,int b,int c,int d){
        double distancia,x1,x2,y1,y2;
        x1=(double) a;
        y1=(double) b;
        x2=(double) c;
        y2=(double) d;
        
        distancia=Math.sqrt((Math.pow((x1-x2),2))+(Math.pow((y1-y2),2)));
        
        return distancia;
    }
    
    public ArrayList<Objecto> getLista_objectos(){
        return lista_objectos;
    }
    
    public ArrayList<Objecto> getPercepcoes(){
        return total_cvisao;
    }
    
    /**Método que calcula a distancia total percorrida pelo agente, através da
     * lista de coordenadas por onde o agente passou.
     */
    public void distancia_percorrida(){
        for(int i=0;i<percurso.size()-1;i++){
            distancia_percorrida=distancia_percorrida+distancia(percurso.get(i).getX(),percurso.get(i).getY(),percurso.get(i+1).getX(),percurso.get(i+1).getY());
        }
    }
    
    /**Método que adiciona à lista de campo de visão todos os objectos que se
     * encontrem dentro do campo de visão do agente.
     * No fim remove os objectos já vistos anteriormente e adiciona-os as
     * percepções.
     * 
     * @param objectos - Lista de todos os objectos no ambiente
     */
    protected void visao(ArrayList<Objecto> objectos){
        lista_cvisao=new ArrayList<Objecto>();
        double distancia;
        
        for(int i=0;i<objectos.size();i++){
            distancia=distancia(objectos.get(i).coordenadas.getX(),objectos.get(i).coordenadas.getY(),coordenadas.getX(),coordenadas.getY());
            if(distancia<=cvisao && distancia>0){ // >0 para não ver objecctos no sitio onde está
                lista_cvisao.add(objectos.get(i));
            }
        }
        lista_cvisao.removeAll(lista_objectos);  //remove os objectos ja vistos
        total_cvisao.addAll(lista_cvisao);  //adiciona a memoria todos os objectos vistos
    }
    
    /**Método que calcula o número de objectos diferentes aprendidos pelo agente.
     * Faz uma cópia da lista de objectos aprendidos e compara um objecto com o
     * seguinte. Se o objecto for igual é removido da lista.
     * No fim o tamanho da lista é o número de objectos diferentes
     */
    public void objectos_diferentes(){
        ArrayList <Objecto> objc_diferentes=new ArrayList<Objecto>();
        objc_diferentes.addAll(lista_objectos);
        
        for(int i=0;i<objc_diferentes.size()-1;i++){
            for(int j=i+1;j<objc_diferentes.size();j++){
                if(objc_diferentes.get(i).cor.compareTo(objc_diferentes.get(j).cor)==0){
                    if(objc_diferentes.get(i).forma.compareTo(objc_diferentes.get(j).forma)==0){
                        if(objc_diferentes.get(i).getTipo().compareTo(objc_diferentes.get(j).getTipo())==0){
                            objc_diferentes.remove(j);
                        }
                    }
                }         
            }
        }
        n_objc_diferentes=objc_diferentes.size();
    }
    
    public void objectos_apreendidos(){
        n_objc_apreendidos=lista_objectos.size();
    }
    
    public String toString(){
        String desc_percurso="";
        
        for(int i=0;i<percurso.size();i++){
            desc_percurso=desc_percurso+"["+percurso.get(i).getX()+","+percurso.get(i).getY()+"]";
        }
        
        return super.toString()+"\nCampo de Visão: "+cvisao+"\nDistância Percorrida: "+distancia_percorrida+"\nNº Objectos Apreendidos: "+n_objc_apreendidos+"\nNº Objectos diferentes: "+n_objc_diferentes+"\nPercurso: "+desc_percurso;
    }
          
}
