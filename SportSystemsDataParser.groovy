// Look into the page source where a xml url is available as the data source.
// Grab the content and save it locally in a file.
// Run 'groovy SportSystemsDataParser.groovy input.xml output.csv' 
// Upload the result csv file to serpie base and follow the instructions. 

def root = new XmlSlurper().parse(new File(args[0]))
def output = new File(args[1])
output.write ''
output << "position,guntime,chiptime,fname,lname,category,number,grade,club\r\n"
def columns = [0, 1, 6, 2, 4, 5, 7]
root.row.each { r ->
	columns.each { c ->
		if (c != 0) {
			output << ','
		}
		if (c == 2) {
			def v = r.cell[c].text()
			def names = v.substring(v.indexOf('>') + 1, v.indexOf('</a>')).split()
			output << names[0]
			output << ','
			output << names[1]
		}
		else {
			output << r.cell[c].text()
		}
	}
	output << ",Serpentine\r\n"
}
