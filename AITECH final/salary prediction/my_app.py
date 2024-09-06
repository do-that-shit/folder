import numpy as np 
from flask import Flask, request, jsonify, render_template
from flask_bootstrap import Bootstrap
import pickle

app = Flask(__name__) # passing default arguement __name__ which helps flask to locate root path for application 
model = pickle.load(open('model.pkl', 'rb')) #using the model that was saved using pickle.dump

@app.route('/')
def home():
	return render_template('index.html')


@app.route('/predict', methods=['POST'])
def predict():
	int_features = [int(x) for x in request.form.values()] # each value received from input is converted into integer 
	final_features = [np.array(int_features)] #list of integers converted into nupy array
	prediction = model.predict(final_features) # using loaded ml model to show model's output for input using predict function
	output = np.round(prediction[0], 2) #rounds prediction to 2 decimal places
	return render_template('index.html', prediction_text='Employee Salary Should Be $ {}'.format(output)) #places output variable at {} placeholder




if __name__ == "__main__": # if script ran directly then only deploy if imported dont deploy
	app.run(debug=True)

