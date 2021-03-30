package fabricadesoftware.com.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView


class ScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private var mScannerView: ZXingScannerView? = null
    @Override
    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)
    }

    @Override
    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this)
        mScannerView!!.startCamera()
    }

    @Override
    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()
    }

    @Override
    override fun handleResult(result: Result) {
        Log.i("QRCode", result.text)
        mScannerView!!.resumeCameraPreview(this)
    }
}