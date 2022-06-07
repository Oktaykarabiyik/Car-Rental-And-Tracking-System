import React, {useState,useEffect} from "react";
//import ReactDOM from "react-dom";

function Post(){
    const[error,sertError]=useState(null);
    const[isLoaded, setIsLoaded]=useState(false);
    const[postList,setPostList]=useState([]);
    useEffect(()=>{
    fetch("/users")//api call datayı çekiyo
    .then(res=>res.json())
    .then(
        (result)=>{
            setIsLoaded(true);//data çekildi onayı
            setPostList(result);//çekilen datayı liste atıyo
        },
        (error)=>{
            setIsLoaded(true);
            sertError(error);
        }
    )
    },[])
    if (error){
        return <div>ERROR!!</div>;
    }
    else if(!isLoaded){ 
        return <div>Loading...</div>;
       
    }
    //html kodlarının arasına js kodu yazmak için {} kullanıyoruz. fonks kullanıyoz.
    else{
        return(
        <ul> 
            < li > elma </ li >
            {postList.map(post=>(
                <li>
                    {post.userName} {post.password}
                </li>
            ))}
        </ul>);
    }
}
export default Post;