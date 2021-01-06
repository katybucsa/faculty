#include "Export.h"
#include <fstream>


void exportToHTML(const std::string & fName, const std::vector<Oferta>& v)
{
	std::ofstream out(fName, std::ios::trunc);
	if (!out.is_open())
		throw FileException("Nu se poate deschide " + fName);
	out << "<html><body>" << std::endl;
	out << "<table border=\"3\" style=\"width:200 % \">" << std::endl;
	for (const auto& o : v) {
		out << "<tr>" << std::endl;
		out << "<td>" << o.getName() << "</td>" << std::endl;
		out << "<td>" << o.getDestination() << "</td>" << std::endl;
		out << "<td>" << o.getType() << "</td>" << std::endl;
		out << "<td>" << o.getPrice() << "</td>" << std::endl;
		out << "</tr>" << std::endl;
	}
	out << "</table>" << std::endl;
	out << "</body></html>" << std::endl;
	out.close();
}
