#include <iostream>
#include <stdio.h>
#include <opencv2/opencv.hpp>
#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include "Blur_CUDA.h"
#include "lodepng.h"


using namespace std;
using namespace cv;


int main(int argc, char** argv) {

	// Read the arguments
	const char* input_file = "ex.png";
	const char* output_file = "b.png";

	std::vector<unsigned char> in_image;
	unsigned int width, height;

	// Load the data
	unsigned error = lodepng::decode(in_image, width, height, input_file);
	if (error) std::cout << "decoder error " << error << ": " << lodepng_error_text(error) << std::endl;

	// Prepare the data
	unsigned char* input_image = new unsigned char[(in_image.size() * 3) / 4];
	unsigned char* output_image = new unsigned char[(in_image.size() * 3) / 4];
	int where = 0;
	for (int i = 0; i < in_image.size(); ++i) {
		if ((i + 1) % 4 != 0) {
			input_image[where] = in_image.at(i);
			output_image[where] = 255;
			where++;
		}
	}

	// Run the filter on it
	filter(input_image, output_image, width, height);

	// Prepare data for output
	std::vector<unsigned char> out_image;
	for (int i = 0; i < in_image.size(); ++i) {
		out_image.push_back(output_image[i]);
		if ((i + 1) % 3 == 0) {
			out_image.push_back(255);
		}
	}
	// Output the data
	error = lodepng::encode(output_file, out_image, width, height);

	//if there's an error, display it
	if (error) std::cout << "encoder error " << error << ": " << lodepng_error_text(error) << std::endl;


	delete[] input_image;
	delete[] output_image;
	return 0;

}