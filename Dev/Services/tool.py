import os
path = 'C:/Users/admin/Downloads/Services'
files = os.listdir(path)

for index, file in enumerate(files):
    fileNew = file.replace("Repository", "Service")
    os.rename(file, fileNew)