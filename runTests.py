#!/usr/bin/env python3
import docker
import os 

dir_path = os.path.dirname(os.path.realpath(__file__))

client = docker.from_env()

def runExample(number: int):
	print("[%7d] " % number, end="")
	with open('examples/%s/output.txt' % number, 'r') as content_file:
	    expected = content_file.read()

	res = client.containers.run(
		image="jotlin",
		command="examples/%s/input.jt" % number,
		volumes={
			dir_path: {'bind': '/dir', 'mode': 'ro'}
		})

	actual = res.decode('ascii')

	if actual != expected:
		print("Failed")
		print("=-=-=-=-=-=-EXPECTED=-=-=-=-=-=-")
		print(expected)
		print("=-=-=-=-=-=-ACTUAL=-=-=-=-=-=-=-")
		print(actual)
		print("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-")
	else:
		print("Passed")

for f in os.listdir("examples"):
	runExample(int(f))
