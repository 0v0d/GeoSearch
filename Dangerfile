if git.lines_of_code > 400
  warn('oops, this PR is big')
else
  message('every thing is fine, happy coding')
end

checkstyle_format.base_path = Dir.pwd
checkstyle_format.report 'app/build/ktlint.xml'