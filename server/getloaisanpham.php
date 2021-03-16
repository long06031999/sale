<?php
    include "connect.php";
    class Loaisp{
        function Loaisp($id,$tenLoaisp,$hinhanhLoaisp)
        {
            $this->id=$id;
            $this->tenloaisp=$tenLoaisp;
            $this->hinhanhloaisp=$hinhanhLoaisp;
        }
    }
    $query ="SELECT * FROM loaisanpham";
    $data = mysqli_query($con,$query);
    $mangloaisanpham=array();
    while($row=mysqli_fetch_assoc($data))
    {
        array_push($mangloaisanpham,new Loaisp(
            $row['id'],
            $row['tenloaisanpham'],
            $row['hinhanhloaisanpham']));
    }
    echo json_encode($mangloaisanpham);
    
?>