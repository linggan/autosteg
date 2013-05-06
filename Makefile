INCLUDES = -I/usr/lib/jvm/java-openjdk/include -I/System/Library/Frameworks/JavaVM.framework/Versions/A/Headers/
LDFLAGS = -L /usr/local/Cellar/jpeg/8d/lib
CFLAGS = -g -Wall
SOURCES =  jutil.c dct.c newcommon.c spotf5.c

all: class headers libspotf5.jnilib

class:
	javac AutoSteg.java

headers:
	javah -jni AutoSteg

spotf5.o:
	gcc  $(INCLUDES) $(CFLAGS) -c spotf5.c

newcommon.o:
	gcc  $(INCLUDES) $(CFLAGS) -c newcommon.c

dct.o:
	gcc  $(INCLUDES) $(CFLAGS) -c dct.c

jutil.o:
	gcc  $(INCLUDES) $(CFLAGS) -c jutil.c

f5.o:
	gcc  $(INCLUDES) $(CFLAGS) -c f5.c

libspotf5.jnilib: spotf5.o newcommon.o dct.o jutil.o f5.o
	gcc $(LDFLAGS) -dynamiclib -o libspotf5.jnilib spotf5.o newcommon.o dct.o jutil.o f5.o -ljpeg -framework JavaVM



