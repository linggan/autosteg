#include "AutoSteg.h"
#include <stdio.h>
#include <string.h>
#include "f5.h"
#include "newcommon.h"

JNIEXPORT jdouble JNICALL Java_AutoSteg_spotf5(JNIEnv *env, jclass clazz, jstring name){
  		char *filename = (*env)->GetStringUTFChars(env, name, NULL);
  		double beta;
  		if (jpg_open(filename) == -1) {
            printf("Couldn't open %s\n", filename);
            return beta == -1;
        }

        beta = detect_f5(filename);
        char tmp[80];
        int stars;

        char outbuf[1024];
        sprintf(outbuf, "%s :", filename);

        if (beta < 0.25) {
            //no f5 detected
        } else {
            //f5 detected
            stars = 1;
            if (beta > 0.25)
                stars++;
            if (beta > 0.4)
                stars++;

            snprintf(tmp, sizeof(tmp), "\n\tCODE RED CODE RED:  f5[%f] DETECTED", beta);
            //strlcat(outbuf, quality(tmp, stars), sizeof(outbuf));
            //printf("%s\n", outbuf);
    }
    return beta;
}


