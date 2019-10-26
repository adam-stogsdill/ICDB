import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt
from keras_preprocessing.image import ImageDataGenerator

# SET NUMBER OF CATEGORIES
number_of_categories = 0

# SET DIRECTORIES
TRAINING_DIR = ""
VALIDATION_DIR = ""

training_data_generator = ImageDataGenerator(
    rescale=1./255,            # rescale image rgb values
    rotation_range=40,         # rotation of image
    width_shift_range=0.2,     # horizontal shift
    height_shift_range=0.2,    # vertical shift
    shear_range=0.2,           # shear rotate range
    zoom_range=0.2,            # zoom transform
    horizontal_flip=True,      # horizontal flip
    fill_mode='nearest'        # fill mode for translations
)

validation_data_generator = ImageDataGenerator(
    rescale=1./255
)

training_from_directory = training_data_generator.flow_from_directory(
    TRAINING_DIR,
    target_size=(256, 256),
    class_mode='categorical'
)

validation_from_directory = validation_data_generator.flow_from_directory(
    VALIDATION_DIR,
    target_size=(256, 256),
    class_mode='categorical'
)

"""
Model's structure
2D Conv Layer
[64 filter, 3x3 kernal, ReLU activation function, input_shape 256x256]

Max Pooling Layer
[Pool-size 2x2]

2D Conv Layer
[64 filter, 3x3 kernal, ReLU activation function]

Max Pooling Layer
[Pool-size 2x2]

2D Conv Layer
[128 filter, 3x3 kernal, ReLU activation function]

Max Pooling Layer
[Pool-size 2x2]

Flatten Layer

Dropout
[50% dropout probability to avoid overfitting of weights during the training]

Dense Layer
[512 neurons, ReLU activation function]

Output Layer
[number of categories, softmax activation function]
"""

model = tf.keras.models.Sequential([
    tf.keras.layers.Conv2D(64, (3, 3), activation='relu', input_shape=(256, 256)),
    tf.keras.layers.MaxPool2D(2, 2),
    tf.keras.layers.Conv2D(64, (3, 3), activation='relu'),
    tf.keras.layers.MaxPool2D(2, 2),
    tf.keras.layers.Conv2D(128, (3, 3), activation='relu'),
    tf.keras.layers.MaxPool2D(2, 2),
    tf.keras.layers.Flatten(),
    tf.keras.layers.Dropout(.5),
    tf.keras.layers.Dense(512, activation='relu'),
    tf.keras.layers.Dense(number_of_categories, activation='softmax')
])

model.compile(loss='categorical_crossentropy', optimizer='rmsprop', metrics=['accuracy'])

end_product = model.fit_generator(training_from_directory, epochs=25, validation_data=validation_from_directory, verbose=1)

