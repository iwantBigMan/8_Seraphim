import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.service.autofill.UserData
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.android.contactproject.R
import com.android.contactproject.contactlist.UserDataModel
import com.android.contactproject.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding
    private var selectedImageUri: Uri? = null
    private var newImage: Int = R.drawable.jisoo


    private val REQUEST_CODE_PICK_IMAGE = 101

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val userData = arguments?.getParcelable<UserDataModel>("UserData")

        binding.myImage.setImageResource(R.drawable.jisoo)
        binding.myName.text = userData?.name
        binding.myMobile.text = userData?.ph
        binding.myEvent.text = "5분뒤 알림"

        binding.btnRevise.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("수정")
            builder.setIcon(R.mipmap.ic_launcher)

            val v1 = layoutInflater.inflate(R.layout.mypage_revise_dialog, null)
            val dialog = AlertDialog.Builder(requireContext())
                .setTitle("수정")
                .setIcon(R.mipmap.ic_launcher)
                .setView(v1)
                .create()
            val setimage = v1.findViewById<ImageView>(R.id.dialog_image2)

            setimage.setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "image/*"
                startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
            }

            val btnAccept = v1.findViewById<Button>(R.id.dialog_acceptbtn)
            val btnCancel = v1.findViewById<Button>(R.id.dialog_cancelbtn)

            btnAccept.setOnClickListener {


                val editTextName = v1.findViewById<EditText>(R.id.dialog_name2)
                val editTextPhoneNumber = v1.findViewById<EditText>(R.id.dialog_phone2)
                val editTextEvent = v1.findViewById<EditText>(R.id.dialog_event2)
                val editImage = v1.findViewById<ImageView>(R.id.dialog_image2)
                val newName = editTextName.text.toString()
                val newPhoneNumber = editTextPhoneNumber.text.toString()
                val newEvent = editTextEvent.text.toString()
                val newImageUri = selectedImageUri

                editImage.setImageURI(newImageUri)


                binding.myName.text = newName
                binding.myMobile.text = newPhoneNumber
                binding.myEvent.text = newEvent
                binding.myImage.setImageResource(newImage)

                dialog.dismiss()
            }

            btnCancel.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
            }
        }
    }


//    private fun getRealPathFromURI(uri: Uri): String? {
//        val projection = arrayOf(MediaStore.Images.Media.DATA)
//        val cursor = requireContext().contentResolver.query(uri, projection, null, null, null)
//        val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//        cursor?.moveToFirst()
//        val filePath = cursor?.getString(columnIndex ?: -1)
//        cursor?.close()
//        return filePath
//    }

}