import Personaje from "../gameObjects/personaje.js";
import Portal from "../gameObjects/portal.js";
//import room from "../../js/room.js";

var azul;
var naranja;
var ron = portal;

class Scene_play extends Phaser.Scene{
    constructor(){
        super({key: "Scene_play"});
    }
    preload() {
        console.log("se cargo scene play");
    } 
    

    create(){
        //mapa
        
        this.mapa = this.make.tilemap({key: "mapa"});
        var tilesets = this.mapa.addTilesetImage("test-mapa","tiles");

        this.fondo = this.mapa.createLayer("fondo",tilesets,0,0);
        this.bloques = this.mapa.createLayer("bloques",tilesets,0,0);

        

        this.bloques.setCollisionByProperty({solido:true});

        // vrear personaje
        this.personaje = new Personaje(this,0,0,"goku")

        this.anims.create({
                key: 'caminar',
                frames: this.anims.generateFrameNumbers('goku', { start: 1, end: 8 }),
                frameRate: 10
            });

        console.log(this.personaje.width+"esto es ancho");
        console.log(this.personaje.body.width+"esto es ancho body");
        console.log(this.personaje.type);
        
        this.io = this.mapa.createLayer("io",tilesets,0,0);
        
        // crear eventps de teclado

        this.cursor = this.input.keyboard.createCursorKeys();

        //portales
        //portales en el suelo
        azul = new Portal(this,286,512,"azul");
        naranja = new Portal(this,544,512,"naranja");

        //techo suelo
        //azul = new Portal(this,286,512,"azul");
        //naranja = new Portal(this,286,32,"naranja");

        //suelo pared
        //azul = new Portal(this,318,480,"azul");
        //naranja = new Portal(this,544,512,"naranja");
       
       
        azul.setAngleBox(90);
        naranja.setAngleBox(90);
        
        
        
        

        console.log(azul.angle+" "+naranja.angle+" angulos azul y naranja");
        console.log(naranja.name+" el nombre");


        //colision

        this.physics.add.collider(this.personaje,this.bloques,bloquesColision,condicionColision,this);

        this.physics.add.overlap(this.personaje, azul, colisionPortalAndPersonaje, overlapPortalAndPersonaje, this);

        this.physics.add.overlap(this.personaje, naranja, colisionPortalAndPersonaje, overlapPortalAndPersonaje, this);

        
        
        

        

        

    }
    update(){
        if(this.cursor.left.isDown){
            if(ron.getPartida()) {
                this.personaje.body.setVelocityX(-150);
                this.personaje.flipX = true;
                ron.moverPerspnaje(this.personaje.body.x, this.personaje.body.y);
            }
        } else if(this.cursor.right.isDown){
            if(ron.getPartida()) {
                this.personaje.body.setVelocityX(150);
                this.personaje.flipX = false;
                ron.moverPerspnaje(this.personaje.body.x, this.personaje.body.y);
            }
        }else{
            this.personaje.body.setVelocityX(0);
        }
        if((this.cursor.left.isDown || this.cursor.right.isDown) && this.personaje.body.onFloor()){
            this.personaje.anims.play('caminar',true);
        }else if (!this.personaje.body.onFloor()){
            this.personaje.setFrame(9);
        }else{
            this.personaje.setFrame(0);
        }
        if (this.cursor.up.isDown &&  this.personaje.body.onFloor()){

                    this.personaje.body.setVelocityY(-200);
                }
                if(this.cursor.down.isDown){
                    console.log("desac");


                }

    }
}

function overlapPortalAndPersonaje(personaje,portal) {
    
    var entrar = (portal.angle==0 && personaje.body.velocity.x<0) ||
                        (portal.angle ==90 && (personaje.body.velocity.y>0 || personaje.body.onFloor())) ||
                        ((portal.angle == 180 || portal.angle==-180) && personaje.body.velocity.x>0) ||
                        ((portal.angle==270 || portal.angle==-90) && personaje.body.velocity.y<0);

    console.log( (portal.angle ==90 && (personaje.body.velocity.y>0 || personaje.body.onFloor()))+" entrar "+ entrar)
    return entrar;
}

function  colisionPortalAndPersonaje(personaje,portal) {
    var otroPortal = portal.name=="azul"?naranja:azul;
    personaje.inPortal=true;
    teleport(personaje,otroPortal)
    conservacionDeVelocidad(personaje,otroPortal,portal);

}
function teleport(personaje,otroPortal) {
     if(otroPortal.angle==0){
        personaje.x=otroPortal.x+personaje.width/2-5;
        personaje.y=otroPortal.y;
    }else if(otroPortal.angle==90){
        personaje.x=otroPortal.x;
        personaje.y=otroPortal.y-personaje.height/2;
    }else if(otroPortal.angle==180 || otroPortal.angle==-180){
        personaje.x=otroPortal.x-personaje.width/2;
        personaje.y=otroPortal.y;
    }else if(otroPortal.angle==270 || otroPortal.angle==-90 ){
        personaje.x=otroPortal.x;
        personaje.y=otroPortal.y+personaje.height/2;
        console.log(personaje.body.velocity.y+" velocityY")
        console.log("hola")
    }
    personaje.inPortal=false;
}
function conservacionDeVelocidad(personaje,otroPortal,portal){
    if(portal.angle==otroPortal.angle){
        if(portal.angle==90 || portal.angle==270 || portal.angle==-90){
            console.log(personaje.body.velocity.y+" || "+personaje.body.velocity.x);
            personaje.body.velocity.y=personaje.body.velocity.y*-1-22.1;
            if(personaje.body.velocity.y==0 && personaje.body.onFloor()){
                personaje.body.velocity.y-=200
            }

        }
        
    }
}

function bloquesColision(personaje,bloques) {
    
}

function condicionColision(personaje,bloques) {
    
    return !personaje.inPortal;
}

export default Scene_play;