using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using CompanyApi.Models;

[Route("api/[controller]")]
[ApiController]
public class DepartmentsController : ControllerBase
{
    private readonly CompanyDbContext _context;
    public DepartmentsController(CompanyDbContext context)
    {
        _context = context;
    }

    // GET: api/Department
    [HttpGet]
    public async Task<ActionResult<IEnumerable<DepartmentDTO>>> GetDepartment()
    {
        return await _context.Departments
            .Select(d => DepartmentToDTO(d))
            .ToListAsync();
    }

    // GET: api/Department/5
    [HttpGet("{departmentid}")]
    public async Task<ActionResult<DepartmentDTO>> GetDepartment(int departmentid)
    {
        var department = await _context.Departments.FindAsync(departmentid);
        if (department == null)
        {
            return NotFound();
        }

        return DepartmentToDTO(department);
    }

    // PUT: api/Department/5
    // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
    [HttpPut("{departmentid}")]
    public async Task<IActionResult> PutDepartment(int departmentid, DepartmentDTO departmentDTO)
    {
        if (departmentid != departmentDTO.DepartmentId)
        {
            return BadRequest();
        }

        _context.Entry(DTOToDepartment(departmentDTO)).State = EntityState.Modified;

        try
        {
            await _context.SaveChangesAsync();
        }
        catch (DbUpdateConcurrencyException)
        {
            if (!DepartmentExists(departmentid))
            {
                return NotFound();
            }
            else
            {
                throw;
            }
        }

        return NoContent();
    }

    // POST: api/Department
    // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
    [HttpPost]
    public async Task<ActionResult<DepartmentDTO>> PostDepartment(DepartmentDTO departmentDTO)
    {
        var department = DTOToDepartment(departmentDTO);
        _context.Departments.Add(department);
        await _context.SaveChangesAsync();

        departmentDTO.DepartmentId = department.DepartmentId;

        return CreatedAtAction("GetDepartment", new { id = departmentDTO.DepartmentId }, departmentDTO);
    }

    // DELETE: api/Department/5
    [HttpDelete("{departmentid}")]
    public async Task<ActionResult<DepartmentDTO>> DeleteDepartment(int departmentid)
    {
        var department = await _context.Departments.FindAsync(departmentid);
        if (department == null)
        {
            return NotFound();
        }

        _context.Departments.Remove(department);
        await _context.SaveChangesAsync();

        return DepartmentToDTO(department);
    }

    private bool DepartmentExists(int? departmentid)
    {
        return _context.Departments.Any(e => e.DepartmentId == departmentid);
    }

    private static DepartmentDTO DepartmentToDTO(Department department) =>
    new DepartmentDTO
    {
        DepartmentId = department.DepartmentId,
        Name = department.Name ?? string.Empty
    };

    private static Department DTOToDepartment(DepartmentDTO departmentDTO) =>
        new Department
        {
            DepartmentId = departmentDTO.DepartmentId,
            Name = departmentDTO.Name
        };
}
