class Bootloader extends Phaser.Scene{
    constructor(){
        super({key: "Bootloader"});
    }
    preload(){
        console.log("se ha cargado boot")

        this.load.on("complete",()=>{
            this.scene.start("Scene_play");
        });
        this.load.spritesheet('goku', 'assets/personaje1.png', { frameWidth: 57, frameHeight: 62 });
        this.load.tilemapTiledJSON("mapa","assets/maps/mapa.json");
        this.load.image("tiles","assets/maps/test-mapa.png");
        this.load.image("azul","assets/azul.png");
        this.load.image("naranja","assets/naranja.png");

    }
    
}

export default Bootloader;