class Disparo extends Phaser.GameObjects.Sprite{
    constructor(scene,x,y,type){
        super(scene,x,y,type);
        this.name = type;
        this.setScale(0.7);
        this.setSize(this.width*this.scale,this.height*this.scale);
        scene.add.existing(this);
        scene.physics.world.enable(this);
        this.body.setCollideWorldBounds(true);
        this.body.setMaxVelocity(900,900);
    }
}

export default Disparo;