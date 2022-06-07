import React, { useEffect} from "react";
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import Stack from '@mui/material/Stack';
import DateTimePicker from '@mui/lab/DateTimePicker';
import {FormControl, Input, Button, FormHelperText,Form,  FormGroup,  ControlLabel} from "@material-ui/core"
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
//import{GoogleMap,Marker,google} from "@react-google-maps"->uninstall;
import SimpleMap from "../../MapComponent/SimpleMap";

function Home(){
    //const [clearedDate, setClearedDate] = React.useState(null);
    //const [endDate, setEndDate] = React.useState(null);
  const [endvalue, setendValue] = React.useState('');
  const [startvalue, setstartValue] = React.useState('');
  const [vehicleId, setVehicleId] = React.useState('');
  const[mapData1,setMapData1]=React.useState([]);
  const[mapData,setMapData]=React.useState([]);
  const[mapData2,setMapData2]=React.useState([]);
  const[vehicleList,setVehicleList]=React.useState([]);
  const handleChangeStart = (e)=> {
   setstartValue(e.target.value);
  };
  const handleChangeEnd = (e)=> {
    setendValue(e.target.value);
   };
  const handleChangeId = (e)=> {
    setVehicleId(e.target.value);
    console.log(e)
   };
  
   const sendRequest = () => {
    fetch('http://127.0.0.1:8081/locations/getByDate?'+new URLSearchParams({
      vehicleId: vehicleId,
      startDate: startvalue,
      endDate: endvalue,
    }),{
      headers:{
        Authorization:'Bearer '+localStorage.getItem("tokenKey")
      }
    })
        .then((res) => res.json())
        .then((result) => {
          console.log(result)
          setMapData(result)
        })
        .catch((err) => console.log(err))
  }

  const getVehicles = () => {
    fetch('http://127.0.0.1:8081/userVehicle?'+new URLSearchParams({
      userId: localStorage.getItem("currentUser"),
    }),{
      headers:{
        Authorization:'Bearer '+localStorage.getItem("tokenKey")
      }
    })
        .then((res) => res.json())
        .then((result) => {
        console.log(result)
        setVehicleList(result)
        getLast30MinutesRequest1(result[0].vehicle_id)
        getLast30MinutesRequest2(result[1].vehicle_id)
        })
        .catch((err) => console.log(err))
  }
  
  const getLast30MinutesRequest1 = (vehicle_id) => {
    fetch('http://127.0.0.1:8081/lastMinutes/Last30Minutes?'+new URLSearchParams({
      vehicleId: vehicle_id,
    }),{
      headers:{
        Authorization:'Bearer '+localStorage.getItem("tokenKey")
      }
    })
        .then((res) => res.json())
        .then((result) => {
          console.log(result)
          setMapData1(result)
        })
        .catch((err) => console.log(err))
  }
  const getLast30MinutesRequest2 = (vehicle_id) => {
    fetch('http://127.0.0.1:8081/lastMinutes/Last30Minutes?'+new URLSearchParams({
      vehicleId: vehicle_id,
    }),{
      headers:{
        Authorization:'Bearer '+localStorage.getItem("tokenKey")
      }
    })
        .then((res) => res.json())
        .then((result) => {
          console.log(result)
          setMapData2(result)
        })
        .catch((err) => console.log(err))
  }
  useEffect(() => {
    if(localStorage.getItem('currentUser')){
      getVehicles()
      // console.log(vehicleList[0].vehicle_id)
      //BURAYA YENİ YAZILACAK METOD ÇAĞIRILACAK 
    }
      
  }, [])
  

  const carList=vehicleList.map((vehicle)=>{
    return <MenuItem key={vehicle.vehicle_id} value={vehicle.vehicle_id}>{vehicle.vehicle_id}</MenuItem>
  }) 

    return(
  
<div className="container" style={{display:"flex",flexDirection:"row"}}>

         <Box 
         
          component="form"  method="GET"
          sx={{ '& .MuiTextField-root': { m: 1, width: '30ch'}
          }}
          
          variant="contained"
            style={{marginTop:30,
        color:'white'}}
      
          noValidate
          autoComplete="off"
        >
            <LocalizationProvider dateAdapter={AdapterDateFns}>
      <Stack spacing={4}>
      <TextField 
              required
              name="start"
              id="filled-start"
              label="Start Date"
              variant="filled"
              value={startvalue}
              onChange={handleChangeStart}
            />
     
         <TextField 
              required
              name="end"
              id="filled-end"
              label="End Date"
              value={endvalue}
              onChange={handleChangeEnd}
              variant="filled"
            />
<InputLabel id="demo-simple-select-label">Araç ID</InputLabel>
  <Select
    labelId="demo-simple-select-label"
    id="demo-simple-select"
    value={vehicleId}
    label="vehicleId"
    onChange={handleChangeId} >
    {carList}
  </Select>

            <Button onClick={(e)=>{
             console.log(startvalue)
              console.log(endvalue)
              console.log(vehicleId)
              sendRequest()
              e.preventDefault()
            }} variant="contained"
            
            style={{marginTop:30,
            background:'linear-gradient(45deg,#2196F3 30%,#21CBF3 90%)',
        color:'white', height:40,
        width: 270} } type={"submit"}>FİLTRELE</Button>
           </Stack>
    </LocalizationProvider>
  
        </Box>
      <div>
        <SimpleMap mapData={[mapData1,mapData2,mapData]}/>
        </div>
        
   </div>
      
    )
}
export default Home;