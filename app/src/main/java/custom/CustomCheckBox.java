package custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import me.seewhy.IndexableRecyclerView.R;

/**
 * Created by nguyen.hoai.duc on 7/8/2016.
 */
public class CustomCheckBox extends View {

    private int width = 0;
    private int height = 0;
    private Paint onPaint = new Paint();
    private Paint offPaint = new Paint();
    private boolean state = true;

    public CustomCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        onPaint.setColor(Color.rgb(1, 157, 154));
        offPaint.setColor(Color.rgb(223, 223, 223));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCheck(canvas);
        invalidate();
    }

    private void drawCheck(Canvas canvas){
        if(this.state){
            Paint epaint = new Paint();
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.checked);
            Matrix matrix = new Matrix();
            matrix.setScale(this.width*1.0f/bitmap.getWidth(), this.height*1.0f/bitmap.getHeight());
            canvas.drawBitmap(bitmap, matrix, epaint);
        }else{
            addCircle(canvas, width/2, height/2, width/2, offPaint);
        }

    }

    private void addCircle(Canvas canvas, float cx, float cy, float radius, Paint paint){
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cx, cy, radius, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1.0f);
        canvas.drawCircle(cx, cy, radius, paint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
    }

    // getter and setter function
    public boolean isSelected(){
        return state;
    }

    public void setSelect(boolean _state){
        this.state = _state;
    }

    // event handling
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(action == MotionEvent.ACTION_UP){
            if(this.state){
                this.state = false;
            }else{
                this.state = true;
            }
        }
        return true;
    }

}
