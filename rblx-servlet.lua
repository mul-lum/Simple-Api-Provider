--!strict
local Models = {}
local Servlet = {}
Servlet.__index = Servlet

local function constructor(URL : string)
	local servletObject = {}
	setmetatable(servletObject, Servlet)
	
	servletObject.URL = URL	
	
	return servletObject	
end

function Servlet:post()
	-- post..
end

function Servlet:postWithPath()
	-- post with path..
end

function Servlet:get()
	-- get..
end

function Servlet:getWithPath()
	-- get with path..
end

-------------------------------------------
local JServlet = {}
JServlet.__index = JServlet

export type model = {
	id : number,
	variables : {},
	functions : {},
	name : string
}

export type servletObject = {
	servlets : {},
}

function JServlet.init(path : Instance)
	assert(path == nil, "Path cannot be null")
	local Servlet : servletObject  = {
		servlets = {},
	}
	setmetatable(Servlet, JServlet)
	
	return Servlet
end

function JServlet:createServlet(URL : string)
	return constructor(URL)
end

function JServlet:add(name : string, model : model)
	if Models[name] ~= nil then
		warn("Model cannot be a dupicate")
		return
	end
	
	Models[name] = model	
end

return JServlet
