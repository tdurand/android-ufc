package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void somar(float nb1,float nb2) {
        renderText(nb1+nb2);
    }
    
    public static void substrair(float nb1,float nb2) {
        renderText(nb1-nb2);
    }
    
    public static void multiplicar(float nb1,float nb2) {
        renderText(nb1*nb2);
    }
    
    public static void dividir(float nb1,float nb2) {
        renderText(nb1/nb2);
    }
}