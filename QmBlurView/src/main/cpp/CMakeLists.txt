cmake_minimum_required(VERSION 4.1.2)
project("QmBlur")

add_library(
        QmBlur
        SHARED
        BlurNative.c
)

find_library(
        log-lib
        log
)

find_library(
        jni-graphics-lib
        jnigraphics
)

target_link_libraries(
        QmBlur
        ${log-lib}
        ${jni-graphics-lib}
)

target_link_options(${CMAKE_PROJECT_NAME} PRIVATE "-Wl,-z,max-page-size=16384")