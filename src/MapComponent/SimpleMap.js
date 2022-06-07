import React, { Component } from 'react';
//import GoogleMapReact from 'google-map-react'->unşnstall;
import { MapContainer, TileLayer, Marker, Popup, Polyline } from 'react-leaflet'
import "leaflet/dist/leaflet.css"
import "./map.css"
import L from 'leaflet';
import L2 from 'leaflet';

import { useState } from "react";

import icon from 'leaflet/dist/images/marker-icon.png';
import iconShadow from 'leaflet/dist/images/marker-shadow.png';

// let DefaultIcon = L.icon({
//     iconUrl: icon,
//     shadowUrl: iconShadow
// });
let  LeafIcon = L.icon({
  
    iconUrl: "https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|2ecc71&chf=a,s,ee00FFFF"
  
  
});
let  LeafIcon2 = L2.icon({
  
  iconUrl: "https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|abcdef&chf=a,s,ee00FFFF"


});


L.Marker.prototype.options.icon = LeafIcon;
L.Marker.prototype.options.icon=LeafIcon2;

const SimpleMap=({mapData})=>{
  const fillBlueOptions = { fillColor: 'blue' }
  const blackOptions = { color: 'black' }
  const limeOptions = { color: 'lime' }
  const purpleOptions = { color: 'purple' }
  const redOptions = { color: 'red' } 
  const markerList1=mapData[0].map((location)=>{
    return (<Marker icon={LeafIcon} pathOptions={redOptions}  key={location.vehicleId + location.date} position={[location.latitude,location.longitude]}>
     
        </Marker>)
  })
  const markerList2=mapData[1].map((location)=>{
    return (<Marker icon={LeafIcon2} pathOptions={purpleOptions } key={location.vehicleId + location.date} position={[location.latitude,location.longitude]}>
         
        </Marker>)
}
) 
const markerList=mapData[2].map((location)=>{
  return (<Marker icon={LeafIcon}  pathOptions={purpleOptions } key={location.vehicleId + location.date} position={[location.latitude,location.longitude]}>
       
      </Marker>)
})



    return(
    
        <div>
          
        <MapContainer center={[59.42190595212989, 17.822133881640568]} zoom={13}>
        <TileLayer
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          
        />
     {   markerList.length==0 ? [markerList1,markerList2]:[markerList]}
        
        {/* <Polyline pathOptions={limeOptions} positions={polyline1} />
        <Polyline pathOptions={purpleOptions} positions={polyline2} /> */}
      </MapContainer>
      </div>
    )
}

export default SimpleMap;