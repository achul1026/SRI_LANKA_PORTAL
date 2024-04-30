function mapboxGl(){
    mapboxgl.accessToken = 'pk.eyJ1IjoidGFleXUiLCJhIjoiY2xpbzVzcWphMDVlZzNlbndxbzQ4a20zMCJ9.Zy8tFFyQruKS8zQKTh3wKA';
    const map = window.map = new mapboxgl.Map({
        container: 'map',
        style:  'mapbox://styles/mapbox/navigation-night-v1',
        zoom: 13,
        center: [79.88617717550125,6.9218463186791155],
        pitch: 0,
        antialias: true,
        sources:"이태유",
    });
}
    
