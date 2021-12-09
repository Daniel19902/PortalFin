let ron = portal;

class Bootloader extends Phaser.Scene{
    constructor(){
        super({key: "Bootloader"});
    }
    preload(){
        console.log("se ha cargado boot")

        this.load.on("complete",()=>{
            this.scene.start("Scene_play");
        });
        this.load.spritesheet('goku', 'assets/'+ron.darSkin()+'.png', { frameWidth: 57, frameHeight: 62});
        this.load.tilemapTiledJSON("mapa","assets/maps/mapa.json");
        this.load.image("tiles1","assets/maps/pista.png");
        this.load.image("tiles","assets/maps/test-mapa.png");
        this.load.image("azul","assets/azul.png");
        this.load.image("naranja","assets/naranja.png");
        this.load.image("azul2","assets/azul.png");
        this.load.image("naranja2","assets/naranja.png");
        this.load.image('ball',"assets/mira.png");
        this.load.audio('songPortal', 'assets/songPortal.mp3');

    }
    
}

export default Bootloader;