//WebGL fingerprinting
var camera, scene, renderer;
try {
    init();
    renderer = animate();
    webGLData = renderer.domElement.toDataURL("image/png");
} catch(e){
    webGLData = "Not supported";
}

function init() {

    //Init scene,camera and materials
    var darkMaterial = new THREE.MeshBasicMaterial( { color: 0xffffcc } );
    var wireframeMaterial = new THREE.MeshBasicMaterial( { color: 0x000000, wireframe: true, transparent: true } );
    var multiMaterial = [ darkMaterial, wireframeMaterial ];

    renderer = new THREE.WebGLRenderer();
    renderer.setSize( 1000, 400 );
    $("#canvas2").append(renderer.domElement);

    camera = new THREE.PerspectiveCamera( 70, window.innerWidth / window.innerHeight, 1, 1000 );
    camera.position.z = 125;
    scene = new THREE.Scene();

    //Cube
    var shape1 = THREE.SceneUtils.createMultiMaterialObject(
        new THREE.BoxGeometry( 50, 50, 50 ),
        multiMaterial );
    shape1.rotation.set(15,15,15);
    scene.add( shape1 );

    //Sphere
    var shape2 = THREE.SceneUtils.createMultiMaterialObject(
        new THREE.SphereGeometry( 40, 32, 16 ),
        multiMaterial );
    shape2.position.set(-100, 0, 0);
    scene.add( shape2 );

    //Torus knot
    var shape3 = THREE.SceneUtils.createMultiMaterialObject(
        // total knot radius, tube radius, number cylinder segments, sides per cyl. segment,
        //  p-loops around torus, q-loops around torus
        new THREE.TorusKnotGeometry( 30, 6, 160, 10, 3, 7 ),
        multiMaterial );
    shape3.position.set(100, 0, 0);
    shape3.rotation.set(15,30,0);
    scene.add( shape3 );

}

function animate() {
    requestAnimationFrame( animate );
    renderer.render( scene, camera );
    return renderer;
}


