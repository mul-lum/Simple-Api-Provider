-- created by MULLUM
local models = {}
local Servlet = {}
setmetatable(Servlet, Servlet)

-- Private Variables
local HTTPService = game:GetService("HttpService")

function Servlet.new(URL : string)
	local servletObject = setmetatable({}, {__index = Servlet})
	
	servletObject.URL = URL;
	
	return servletObject
end

-- Object Functions
function Servlet:get() 
	local response = HTTPService:GetAsync(self.URL)
	
	return HTTPService:JSONDecode(response)
end

function Servlet:getWithPath(extender : string) 
	assert(extender ~= nil, "Arguement 1 can't be nullable")
	local URL : string = self.URL
	local response = HTTPService:GetAsync(self.URL .. extender)
	
	return HTTPService:JSONDecode(response)
end

function Servlet:post(model : {})
	assert(model ~= nil, "Arguement 1 can't be nullable")
	local URL : string = self.URL
	local modelJson = HTTPService:JSONEncode(model)

	local success = HTTPService:PostAsync(URL, modelJson, Enum.HttpContentType.ApplicationJson, false)
end

function Servlet:postWithPath(extender :string, model : {})
	assert(model ~= nil, "Arguement 2 can't be nullable")
	local URL : string = self.URL
	local modelJson = HTTPService:JSONEncode(model)
	
	local success = HTTPService:PostAsync(URL .. extender, modelJson, Enum.HttpContentType.ApplicationJson, false)
	return success
end

function Servlet:getURL()
	return self.URL;
end

----------------------------------------------------------------------------------------------------------------------------------

local JServlet = {}
JServlet.__index = JServlet

JServlet.__VERSION__ = "3.2"
JServlet.__NAME__ = "JAVA-SERVLET"
JServlet.servlets = {}

-- Functions
function JServlet.createServlet(URL : string)
	local servletObject = Servlet.new(URL)
	
	
	JServlet.servlets[URL] = servletObject
	return servletObject
end

function JServlet.addModel(name : string, model : {})
	models[name] = model
end

function JServlet.details()
	print("VERSION - " .. JServlet.__VERSION__ .. JServlet.__NAME__)
end

return JServlet

----------------------------------------------------------------------------------------------------------------------------------
