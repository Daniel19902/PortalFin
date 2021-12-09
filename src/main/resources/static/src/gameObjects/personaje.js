class Personaje extends Phaser.GameObjects.Sprite{
    constructor(scene,x,y,type){
        super(scene,x,y,type);
        this.name = type;
        this.setScale(0.7);

        scene.add.existing(this);
        scene.physics.world.enable(this);
        this.body.setCollideWorldBounds(true);
        this.body.setMaxVelocity(900,900);
        this.inPortal=false;
        this.body.setSize(30,45,true);
    }
}

export default Personaje;