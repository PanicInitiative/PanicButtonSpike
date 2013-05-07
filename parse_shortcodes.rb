require 'nokogiri'
require 'open-uri'
require 'json'

def cleanup(dirty_string)
	dirty_string.gsub("\n",'').gsub("\t",'')
end

doc = Nokogiri::HTML(open('twitter_short_codes.html'))
country_map = {}
current_country = nil

doc.xpath("//tbody/tr").each do |tr|
  tds = tr.xpath("td")  

  if tds.length == 3 
  	country = cleanup(tds[0].content)
  	current_country = country
  	country_map[country] = [{cleanup(tds[1].content) => cleanup(tds[2].content)}]
  else
  	country_map[current_country].push({cleanup(tds[0].content) => cleanup(tds[1].content)})
  end
end
File.open('twitter_short_codes.json', 'w+') {|f| f.write(country_map.to_json) }
