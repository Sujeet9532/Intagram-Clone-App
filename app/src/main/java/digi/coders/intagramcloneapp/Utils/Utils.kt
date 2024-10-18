package digi.coders.intagramcloneapp.Utils

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import com.google.firebase.storage.FirebaseStorage

import java.util.UUID

fun UplodeImage(uri: Uri,FolderName:String,callback:(String?)->Unit){
    var imageUri:String?=null
    FirebaseStorage.getInstance().getReference(FolderName).child(UUID.randomUUID().toString()).putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageUri=it.toString()
                callback(imageUri)
            }


        }


}
fun UplodeVideo(uri: Uri, FolderName:String, progressDialog: ProgressDialog, callback:(String?)->Unit){
   progressDialog.setTitle("Uploading...")
    progressDialog.show()
    var imageUri:String?=null
    FirebaseStorage.getInstance().getReference(FolderName).child(UUID.randomUUID().toString()).putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageUri=it.toString()
                progressDialog.dismiss()
                callback(imageUri)
            }


        }
        .addOnProgressListener {
            var uploadedvalu:Long=it.bytesTransferred/it.totalByteCount
            progressDialog.setMessage("Uploaded ")
        }


}